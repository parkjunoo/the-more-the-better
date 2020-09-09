package io.playdata.themorethebetter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.playdata.themorethebetter.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}