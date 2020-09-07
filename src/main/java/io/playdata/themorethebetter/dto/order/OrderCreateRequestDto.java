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

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderCreateRequestDto {
	private String storeName; //가게이름  
	private Long people; //모집인원 
	private Date time; //모집마감시간 
	private String deliPlace; //배달수령장소 
	private Long minCost; //최소주문가격 
	private String text; //부가설명 
		
	@Builder
	public OrderCreateRequestDto(String storeName, Long people, Date time, String deliPlace, Long minCost, String text) {
		super();
		this.storeName = storeName;
		this.people = people;
		this.time = time;
		this.deliPlace = deliPlace;
		this.minCost = minCost;
		this.text = text;
	}
	
	
}