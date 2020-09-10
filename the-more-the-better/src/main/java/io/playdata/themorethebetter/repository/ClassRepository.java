package io.playdata.themorethebetter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.playdata.themorethebetter.domain.Class;

public interface ClassRepository extends JpaRepository<Class, Long> {
	
	public Optional<Class> findByNo(Long no);

	public Optional<Class> findByCode(String code);
}