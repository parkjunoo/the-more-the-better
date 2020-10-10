package io.playdata.themorethebetter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.playdata.themorethebetter.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	
	// 고유번호로 가게 찾기 
	public Optional<Store> findByNo(Long mem_no);
	
	// 이름으로 가게 찾기 
	public Optional<Store> findByName(String st_name); 

}