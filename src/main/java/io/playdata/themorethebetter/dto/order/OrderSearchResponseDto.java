package io.playdata.themorethebetter.dto.order;

import io.playdata.themorethebetter.domain.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OrderSearchResponseDto {
	private int waitingNum;
	private String storeName;
	private String closeTime;
	private String storeImg;
	private int waitingmems;
	
}
