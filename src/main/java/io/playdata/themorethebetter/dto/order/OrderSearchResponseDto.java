package io.playdata.themorethebetter.dto.order;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class OrderSearchResponseDto {
	private int waitingNum;
	private String storeName;
	private LocalTime closeTime;
	private String storeImg;
	private int waitingmems;
	
}