package io.playdata.themorethebetter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.domain.WaitingMems;

public interface WaitingMemsRepository extends JpaRepository<WaitingMems, Long> {
	
	//주문 정보로 대기자 정보 찾기 
	public Optional<WaitingMems> findByOrder(Waiting order);

}