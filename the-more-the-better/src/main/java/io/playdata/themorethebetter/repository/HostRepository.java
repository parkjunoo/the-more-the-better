package io.playdata.themorethebetter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.playdata.themorethebetter.domain.Host;
import io.playdata.themorethebetter.domain.Member;

public interface HostRepository extends JpaRepository<Host, Long> {
	
	public Optional<Host> findByNo(Long host_no); // 고유번호로 호스트를 찾는 것

}