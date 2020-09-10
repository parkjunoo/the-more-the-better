/*
 * 주문 관리 서비스
 * 	1. 주문 생성 
 * 	2. 주문 완료
 * 	3. 주문 취소 
 * 	4. 주문 상세보기 
 */

package io.playdata.themorethebetter.service;

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
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException("주문자 정보가 올바르지 않습니다."));
		
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
		member.startwaiting(waiting);
	}
	
	/* 대기인원 순으로 주문 전체보기 */
	@Transactional(readOnly=true)
	public List<OrderSearchResponseDto> findAll() {
		return waitingRepository.findAllDesc()
				.map(OrderSearchResponseDto::new)
				.collect(Collectors.toList());
	}
	
	/* 주문 멤버 삭제 - 호스트가 아닌 경우만 가능 */
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
		member.cancelwaiting();
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
		member.cancelwaiting();
		member.deleteHost();
	}
}