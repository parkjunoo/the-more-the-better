package io.playdata.themorethebetter.dto.order;

import java.util.ArrayList;

import io.playdata.themorethebetter.domain.Host;
import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Store;
import io.playdata.themorethebetter.domain.Waiting;
import lombok.Getter;

@Getter
public class OrderSearchResponseDto {
	private Store store;
	private Host host;
	private int minlimit;
	private ArrayList<Member> waitingmems;
	
	public OrderSearchResponseDto(Waiting entity) {
		store = entity.getStore();
		host = entity.getHost();
		minlimit = entity.getMinperson();
		waitingmems = entity.getWaitingmems();
	}

}
