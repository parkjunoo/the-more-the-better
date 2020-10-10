package io.playdata.themorethebetter.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.playdata.themorethebetter.domain.Waiting;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
	// 고유번호로 주문 검색
	public Optional<Waiting> findByNo(Long order_no); 
	
	// 대기인원이 많이 찬 주문 우선으로 모든 주문 검색  
	@Query(value="SELECT w FROM Waiting w ORDER BY w.standby DESC")
	public List<Waiting> findAllStandby();
	
	// 이름으로 주문이 진행중인 가게 찾기 (문자열 포함하는 모든가게 검색) 
	@Query(value="SELECT w FROM Waiting w INNER JOIN w.store s ON w.store = s.no AND s.name LIKE %:name%")
	public ArrayList<Waiting> findByNameContaining(String name);
	
	@Query(value="SELECT w FROM Waiting w INNER JOIN w.store s ON w.store = s.no AND s.name LIKE %:name%")
	public Optional<Waiting> findByName(String name);
	
	// 고유번호로 주문 삭제 
	public void deleteByNo(Long no);	
}