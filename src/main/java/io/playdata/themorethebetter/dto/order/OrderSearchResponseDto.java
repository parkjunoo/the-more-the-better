package io.playdata.themorethebetter.dto.order;

import io.playdata.themorethebetter.domain.Host;
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import io.playdata.themorethebetter.domain.WaitingMems;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import lombok.Getter;

@Getter
public class OrderSearchResponseDto {
	private Store store;
	private Host host;
	private Long minlimit;
	private WaitingMems waitingmems;
	
	public OrderSearchResponseDto(Waiting entity) {
		store = entity.getStore();
		host = entity.getHost();
		minlimit = entity.getMinperson();
		waitingmems = entity.getWaitingmems();
	}
	
	private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
