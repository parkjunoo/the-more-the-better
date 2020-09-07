package io.playdata.themorethebetter.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

import io.playdata.themorethebetter.exception.ForbiddenException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springfox.documentation.spring.web.json.Json;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Waiting extends BaseTimeEntity
{
	@Id @GeneratedValue
	@Column(name="WAIT_NO")
	private Long no; // 주문 고유 번호 
	
	@ManyToOne(optional=false)
	@JoinColumn(name="ST_NO")
	private Store store; // 주문하려는 가게 정보 
	
	@OneToOne(optional=false)
	@JoinColumn(name="HOST_NO")
	private Host host; // 주문하려는 호스트 정보
	
	@ColumnDefault("1")
	@Column(name="WAIT_STANDBY")
	private Long standby;// 현재 주문을 기다리는 인원 
	
	@ColumnDefault("3")
	@Column(name="WAIT_MINPERSON")
	private Long minperson; // 주문 가능한 최소 인원
	
	@Column(name="WAIT_PLACE")
	private String meetplace; //배달 수령 장소 
	
	@JsonFormat(shape=JsonFormat.Shape.STRING ,pattern="a hh:mm")
	@Column(name="WAIT_CLOSETIME")
	private Date closetime; //모집 마감 시간 
	
	@Column(name="WAIT_MINCOST")
	private Long mincost; //최소주문가격
	
	@Column(name="WAIT_TEXT")
	private String text; //부가 설명 
	
	@OneToOne(optional=false)
	@JoinColumn(name="WAIT_MEM_NO")
	private WaitingMems waitingmems; // 대기명단 정보 

	@Builder
	public Waiting(Store store, Host host, Long standby, Long minperson, String meetplace, Date closetime, Long mincost,
			String text, WaitingMems waitingmems) {
		this.store = store;
		this.host = host;
		this.standby = standby;
		this.minperson = minperson;
		this.meetplace = meetplace;
		this.closetime = closetime;
		this.mincost = mincost;
		this.text = text;
		this.waitingmems = waitingmems;
	}
	
	//Default값 설정 
	@PrePersist
    public void prePersist() {
        this.standby = this.standby == null ? 1 : this.standby;
        this.minperson = this.minperson == null ? 3 : this.minperson;
    }
	
	//대기 인원 추가 
	public void addWaitMem() {
		this.standby++;
	}
	
	//대기 인원 삭제 
	public void deleteWaitMem() throws ForbiddenException {
		if(this.standby <= 1) {
			throw new ForbiddenException("주문을 취소할 수 없습니다.");
		}
		this.standby--;
	}
	
}	