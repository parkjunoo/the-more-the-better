/*
 * 주문 관리 서비스
 * 	1. 주문 생성 
 * 	2. 주문 완료
 * 	3. 주문 취소 
 * 	4. 주문 상세보기 
 */

package io.playdata.themorethebetter.service;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.dto.order.OrderSearchResponseDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.StoreRepository;
import io.playdata.themorethebetter.repository.WaitingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class OrderService {
	
	private WaitingRepository waitingRepository;
	private MemberRepository memberRepository;
	private StoreRepository storeRepository;
	
	/* 주문 생성 */
	@Transactional
	public void makeOrder(OrderCreateRequestDto dto, Long mem_no) throws NotFoundException, ForbiddenException, ParseException {
		Store store = Store.builder().name(dto.getStoreName()).address(dto.getStoreAddress()).picture(dto.getStoreImg()).build();
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException("주문자 정보가 올바르지 않습니다."));
		
		log.info("상점생성완료");
		storeRepository.save(store);
		
		log.info("마감시간 String -> Date");
		LocalTime endDate = LocalTime.parse(dto.getTime());
		
		log.info("대기주문 빌드");
		Waiting waiting = Waiting.builder()
				.store(store)
				.host(member)
				.minperson(dto.getPeople())
				.meetplace(dto.getDeliPlace())
				.closetime(endDate)
				.mincost(dto.getMinCost())
				.text(dto.getText())
				.build();
				
		log.info("대기 주문 저장");
		waitingRepository.save(waiting); 
		
		log.info("주문자 호스트 권한 보유 및 대기자 저장");
		member.createHost();
		member.startWaiting(waiting);
	}
	
	/* 대기인원 순으로 주문 전체보기 */
	@Transactional(readOnly=true)
	public List<Waiting> findAllStandBy() {
		log.info("------findAllStandBy()--------");
		return waitingRepository.findAllStandby();
	}
	
	/* 멤버 고유번호로 주문 검색 */
	@Transactional(readOnly=true)
	public Waiting findOrderByMem(long mem_no) throws NotFoundException {
		log.info("멤버 번호로 주문 검색 중...");
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException("멤버 정보가 올바르지 않습니다."));
		
		return member.searchWaiting();
	}
	
	/* 주문통해서 상점 검색 */
	@Transactional(readOnly=true)
	public Store findStoreByOrder(Waiting order) {
		log.info("주문통해서 상점 검색중...");
		return order.getStore();
	}
	
	/* 주문번호로 주문 검색 */
	@Transactional(readOnly=true)
	public Waiting findOrderByNo(long mem_no) throws NotFoundException{
		log.info("주문 번호로 주문 검색 중...");
		Waiting order = waitingRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException("주문 번호가 올바르지 않습니다."));
		
		return order;
	}
	
	/* 존재하던 주문에 대기멤버 저장 */
	@Transactional
	public void updateWaiting(Member mem) {
		log.info("대기 주문 저장");
	    memberRepository.save(mem); 
	}
	
	/* store 이름으로 주문목록 검색 */
	@Transactional(readOnly=true)
	public ArrayList<Waiting> searchByStoreName(String st_name) {
		log.info("------searchByStoreName--------" + "받은 데이터 :" + st_name);
		return waitingRepository.findByNameContaining(st_name); 
		
	}
	
	/* 주문 멤버 삭제 - 호스트 여부 확인 */
	@Transactional
	public void deleteOrder(Long mem_no, Long wait_no) throws NotFoundException, ForbiddenException {
		log.info("호스트 여부 확인중...");
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException());
		Waiting order = waitingRepository.findByNo(wait_no)
				.orElseThrow(() -> new NotFoundException());
		
		if(member.isIshost()) {
			deleteOrderByHost(member, order);
		} else {
			deleteMemberToOrder(member, order);
		}
	}
	
	/* 주문 멤버 삭제 - 호스트가 아닌 경우만 가능 */
	@Transactional
	public void deleteMemberToOrder(Member member, Waiting order) throws ForbiddenException {
		log.info("호스트 아님 : 주문 대기멤버 명단에서 삭제 시도중...");
		
		if(member.getMywait() != order) {
			throw new ForbiddenException("주문정보가 일치하지 않습니다.");
		}
		member.cancelWaiting();
		order.deleteWaitMem();
	}
	
	/* 주문 삭제 - 호스트만 가능 */
	@Transactional
	public void deleteOrderByHost(Member member, Waiting order) throws ForbiddenException {
		log.info("호스트이므로 주문 전체 삭제 시도중...");
		
		//호스트 이외에 대기중인 멤버가 존재하면 주문 취소 불가 
		if(member.getMywait() != order || order.getStandby() != 1) {
			throw new ForbiddenException("주문취소가 불가능합니다.");
		}
		member.cancelWaiting();
		storeRepository.delete(order.getStore());
		waitingRepository.delete(order);
		member.deleteHost();
	}
	
}