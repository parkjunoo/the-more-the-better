/*
 * data() {
    return{
      storeName : "", //가게 이름 
      people: 0, //인원
      time: "", //모집마감시간
      deliPlace: "", //배달수령장소
      minCost: "", //최소주문가격
      text:""//부가설명
    }
  },
 */
package io.playdata.themorethebetter.dto.order;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderCreateRequestDto {
	
	private String storeName; //가게이름  
	private int people; //모집인원 
	private String time; //모집마감시간 
	private String deliPlace; //배달수령장소 
	private int minCost; //최소주문가격 
	private String text; //부가설명 
	private String storeAddress;
	private String storeImg;
		
	@Builder
	public OrderCreateRequestDto(String storeName, int people, String time, String deliPlace, int minCost, String text, String storeAddress, String storeImg) {
		this.storeName = storeName;
		this.people = people;
		this.time = time;
		this.deliPlace = deliPlace;
		this.minCost = minCost;
		this.text = text;
		this.storeAddress = storeAddress;
		this.storeImg = storeImg;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderCreateRequestDto [storeName=");
		builder.append(storeName);
		builder.append(", people=");
		builder.append(people);
		builder.append(", time=");
		builder.append(time);
		builder.append(", deliPlace=");
		builder.append(deliPlace);
		builder.append(", minCost=");
		builder.append(minCost);
		builder.append(", text=");
		builder.append(text);
		builder.append("]");
		return builder.toString();
	}
	
	
}