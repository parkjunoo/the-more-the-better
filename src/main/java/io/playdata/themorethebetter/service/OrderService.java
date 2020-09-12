/*
 * 주문 관리 서비스
 * 	1. 주문 생성 
 * 	2. 주문 완료
 * 	3. 주문 취소 
 * 	4. 주문 상세보기 
 */

package io.playdata.themorethebetter.service;

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
	public void makeOrder(OrderCreateRequestDto dto, Long mem_no) throws NotFoundException, ForbiddenException {
		
		Store store = Store.builder().name(dto.getStoreName()).address(dto.getStoreAddress()).picture(dto.getStoreImg()).build();
		Member member = memberRepository.findByNo(mem_no).orElseThrow(() -> new NotFoundException("주문자 정보가 올바르지 않습니다."));
		
		log.info("상점생성완료");
		storeRepository.save(store);
		
		log.info("대기주문 빌드");
		Waiting waiting = Waiting.builder()
				.store(store)
				.host(member)
				.minperson(dto.getPeople())
				.meetplace(dto.getDeliPlace())
				.closetime(dto.getTime())
				.mincost(dto.getMinCost())
				.text(dto.getText())
				.build();
				
		log.info("대기 주문 저장");
		waitingRepository.save(waiting); 
		
		log.info("주문자 호스트 권한 보유 및 대기자 저장");
		member.createHost();
		member.startWaiting(waiting);
	}
	
	/* 주문통해서 상점 검색 */
	@Transactional(readOnly=true)
	public Store findStoreByOrder(Waiting order) {
		log.info("주문통해서 상점 검색중...");
		return order.getStore();
	}
	
	/*주문번호로 주문 검색*/
	@Transactional(readOnly=true)
	public Waiting findOrderByNo(long mem_no) throws NotFoundException{
		log.info("주문 번호로 주문 검색 중...");
		Waiting order = waitingRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException("주문 번호가 올바르지 않습니다."));
		
		return order;
	}

	
	@Transactional(readOnly=true)
	public Waiting findOrderByMem(long mem_no) throws NotFoundException {
		log.info("멤버 번호로 주문 검색 중...");
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException("멤버 정보가 올바르지 않습니다."));
		
		return member.searchWaiting();
		/*  
		 * return member.getMywait(); 를 쓰지 않은이유 
		 * null일 수도 있는 값을 getter로 찾아오는 방법은 객체지향적이지 않다..? 
		 * 참고 : https://tech.wheejuni.com/2017/12/03/why-no-optional-for-getters/
		 */
	}
	
	
	/* 대기인원 순으로 주문 전체보기 */
	// DB에서 가져온 데이터 OrderSearchResponseDto로 래핑
	@Transactional(readOnly=true)
	public List<OrderSearchResponseDto> findAllStandBy() {
		log.info("------findAllStandBy()--------");
		ArrayList<OrderSearchResponseDto> Waitings = new ArrayList<>();
		ArrayList<String> searchWaitings = waitingRepository.findAllStandby();
		for(int i = 0; i < searchWaitings.size(); i++) {
			String[] temp = searchWaitings.get(i).split(",");
			Waitings.add(
					OrderSearchResponseDto.builder().waitingNum(Integer.parseInt(temp[0]))
													.storeImg(temp[1])
													.storeName(temp[2])
													.closeTime(temp[3])
													.waitingmems(Integer.parseInt(temp[4])).build()
			
			);
		}
		return Waitings;
	}

	/*store 이름으로 주문목록 검색*/
	@Transactional(readOnly=true)
	public ArrayList<String> searchByStoreName(String st_name) {
		log.info("------searchByStoreName--------" + "받은 데이터 :" + st_name);
		return waitingRepository.findByNameContaining(st_name); 
		
	}

	
	/* 주문 멤버 삭제 */
	@Transactional
	public void deleteMemberToOrder(Long mem_no, Long wait_no) throws NotFoundException, ForbiddenException {
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException());
		Waiting order = waitingRepository.findByNo(wait_no)
				.orElseThrow(() -> new NotFoundException());
		
		log.info("현재 참여중인 주문이 아니거나 호스트일 경우 멤버 주문 취소 불가");
		if(member.getMywait() != order && member.isIshost()) {
			throw new ForbiddenException("주문정보가 일치하지 않습니다.");
		}
		member.cancelWaiting();
	}
	
	/* 주문 삭제 - 호스트만 가능 */
	@Transactional
	public void deleteOrder(Long mem_no, Long wait_no) throws NotFoundException, ForbiddenException {
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException());
		Waiting order = waitingRepository.findByNo(wait_no)
				.orElseThrow(() -> new NotFoundException());
		
		log.info("1. 현재 참여중인 주문이 아니거나"
				+ "2. 호스트가 아니거나"
				+"3. 호스트 이외에 대기중인 멤버가 존재한다면"
				+ "주문 취소 불가");
		if(member.getMywait() != order && !member.isIshost() && order.getStandby() != 1) {
			throw new ForbiddenException("주문취소가 불가능합니다.");
		}
		member.cancelWaiting();
		member.deleteHost();
	}
}
