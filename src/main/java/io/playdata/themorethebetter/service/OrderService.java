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

import io.playdata.themorethebetter.domain.Host;
import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.domain.WaitingMems;
import io.playdata.themorethebetter.dto.order.OrderCreateRequestDto;
import io.playdata.themorethebetter.dto.order.OrderSearchResponseDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.repository.HostRepository;
import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.StoreRepository;
import io.playdata.themorethebetter.repository.WaitingMemsRepository;
import io.playdata.themorethebetter.repository.WaitingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class OrderService {
	
	private WaitingRepository waitingRepository;
	private HostRepository hostRepository;
	private WaitingMemsRepository waitingMemsRepository;
	private MemberRepository memberRepository;
	private StoreRepository storeRepository;
	
	/* 주문 생성 */
	@Transactional
	public void makeOrder(OrderCreateRequestDto dto, Long memberNo) throws NotFoundException, ForbiddenException {
		
		log.info("멤버 존재 여부 파악");
		Member member = memberRepository.findByNo(memberNo)
				.orElseThrow(() -> new NotFoundException("존재하지 않는 멤버입니다."));
		
		log.info("가게 존재 여부 파악"); 
		Store store = storeRepository.findByName(dto.getStoreName())
				.orElseThrow(() -> new NotFoundException("존재하지 않는 스토어 명입니다."));
		
		log.info("멤버 현재 중복 주문 진행 여부 파악"); 
		if(member.getHost() != null || member.getWaitingmems() != null) {
			throw new ForbiddenException("이미 주문하신 내역이 존재합니다. 주문을 완료 후 다시 실행해주세요.");
		}
		
		log.info("대기자 정보 빌드");
		WaitingMems waitingmems = WaitingMems.builder()
				.build();
		
		log.info("호스트 빌드");
		Host host = Host.builder()
				.member(member)
				.build();

		log.info("대기주문 빌드");
		Waiting waiting = Waiting.builder()
				.store(store)
				.host(host)
				.waitingmems(waitingmems)
				.minperson(dto.getPeople())
				.meetplace(dto.getDeliPlace())
				.closetime(dto.getTime())
				.mincost(dto.getMinCost())
				.text(dto.getText())
				.build();
		
		log.info("호스트 저장");
		hostRepository.save(waiting.getHost()); 
		
		log.info("대기자 정보 저장");
		waitingMemsRepository.save(waitingmems);
		
		log.info("대기자 정보에 호스트 삽입");
		member.startWaiting(waitingmems);
		
		log.info("대기 주문 저장");
		waitingRepository.save(waiting).getNo(); 		
	}
	
	/* 대기인원 순으로 주문 전체보기 */
	@Transactional(readOnly=true)
	public List<OrderSearchResponseDto> findAll() {
		return waitingRepository.findAllDesc()
				.map(OrderSearchResponseDto::new)
				.collect(Collectors.toList());
	}
	
	/* 스토어명으로 주문 검색 */
	@Transactional(readOnly=true)
	public List<OrderSearchResponseDto> findByStoreName(String st_name) {
		return waitingRepository.findByNameContaining(st_name)
				.map(OrderSearchResponseDto::new)
				.collect(Collectors.toList());
	}
	
	/* 주문 삭제 */
	@Transactional
	public void cancelOrder(Long host_no, Long order_no) throws NotFoundException, ForbiddenException {
		Host host = hostRepository.findByNo(host_no)
				.orElseThrow(() -> new NotFoundException());
		
		if(host.getOrder().getNo() != order_no) {
			throw new ForbiddenException("주문 호스트만 주문을 취소할 수 있습니다.");
		}
		
		waitingRepository.deleteByNo(order_no);
	}

	/* 주문 멤버 추가 */
	@Transactional
	public void addMemberToOrder(Long mem_no, Long wait_no) throws NotFoundException {
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException());
		Waiting order = waitingRepository.findByNo(wait_no)
				.orElseThrow(() -> new NotFoundException());
		
		if(member.getHost() != null || member.getWaitingmems() != null) {
			throw new ForbiddenException("이미 주문하신 내역이 존재합니다. 주문을 완료 후 다시 실행해주세요.");
		}
		
		member.startWaiting(order.getWaitingmems());
		order.addWaitMem();
	}
	
	/* 주문 멤버 삭제 */
	@Transactional
	public void deleteMemberToOrder(Long mem_no, Long wait_no) throws NotFoundException, ForbiddenException {
		Member member = memberRepository.findByNo(mem_no)
				.orElseThrow(() -> new NotFoundException());
		Waiting order = waitingRepository.findByNo(wait_no)
				.orElseThrow(() -> new NotFoundException());
		
		member.cancelWaiting();
		order.deleteWaitMem();
	}
}
