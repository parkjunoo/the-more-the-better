package io.playdata.themorethebetter.repository;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.playdata.themorethebetter.domain.Waiting;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
	// 고유번호로 주문 검색
	public Optional<Waiting> findByNo(Long order_no); 
	
	// 대기인원이 많이 찬 주문 우선으로 모든 주문 검색 
	@Query("SELECT w " +
            "FROM Waiting w " +
            "ORDER BY w.standby DESC")
	public Stream<Waiting> findAllDesc();
	
	// 고유번호로 주문 삭제 
	public void deleteByNo(Long no);	
}