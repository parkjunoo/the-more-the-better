/*
 * 1초마다 자동으로 마감시간 체크 
 */
package io.playdata.themorethebetter.service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.repository.StoreRepository;
import io.playdata.themorethebetter.repository.WaitingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class Scheduler {
	
	private WaitingRepository waitingRepository;
	private StoreRepository storeRepository;

	@Scheduled(fixedRate = 1000)
	@Transactional
	public void checkCloseTime() {
		
		LocalTime currentTime = LocalTime.now();
		log.info("마감시간 확인중... - " + currentTime);
		
		try {
			//전체 주문의 고유번호와 마감시간 가져오기 
			Map<Long, LocalTime> timeMap = new HashMap<>();
			waitingRepository.findAll().stream().forEach(o -> timeMap.put(o.getNo(), o.getClosetime()));
			log.info("timeMap : " + timeMap);
			
			//현재시간과 비교해서 마감시간과 동일하면 주문 삭제 
			for(long order_no : timeMap.keySet()) {
				LocalTime closeTime = timeMap.get(order_no);
				long hour = currentTime.until(closeTime, ChronoUnit.HOURS); //시간간격 
				long minute = currentTime.until(closeTime, ChronoUnit.MINUTES); //분간격 
				
				if(hour == 0 && minute == (long)0) {
					log.info("주문번호 : " + order_no);
					log.info("마감시간과 현재시간이 일치합니다. 주문을 마감합니다.");
					
					Waiting target = waitingRepository.findByNo(order_no).get();
					List<Member> members = target.getWaitingmems();
					
					//주문 대기중인 멤버들 모두 내보내기 
					for(Member member : members) {
						member.cancelWaiting();
						if(member.isIshost()) {
							member.deleteHost();
						}
					}
					//주문 삭제 
					storeRepository.delete(target.getStore());
					waitingRepository.delete(target);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}