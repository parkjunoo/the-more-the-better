package io.playdata.themorethebetter.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import org.hibernate.annotations.ColumnDefault;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.playdata.themorethebetter.exception.ForbiddenException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Waiting extends BaseTimeEntity
{
	@Id @GeneratedValue(strategy= GenerationType.AUTO,generator="native2")
	@Column(name="WAIT_NO")
	private Long no; // 주문 고유 번호 
	
	@OneToOne
	@JoinColumn(name="ST_NO")
	private Store store; // 주문하려는 가게 정보 
	
	@OneToOne(optional=false)
	@JoinColumn(name="MEM_NO")
	private Member host; // 주문하려는 호스트 정보
	
	@ColumnDefault("1")
	@Column(name="WAIT_STANDBY")
	private int standby;// 현재 주문을 기다리는 인원 
	
	@Column(name="WAIT_MINPERSON")
	private int minperson; // 주문 가능한 최소 인원
	
	@Column(name="WAIT_PLACE")
	private String meetplace; //배달 수령 장소 
	
	@Column(name="WAIT_CLOSETIME")
	private LocalTime closetime; //모집 마감 시간 
	
	@Column(name="WAIT_MINCOST")
	private int mincost; //최소주문가격
	
	@Column(name="WAIT_TEXT")
	private String text; //부가 설명 
	
	@OneToMany(mappedBy="mywait")
	private List<Member> waitingmems = new ArrayList<Member>(); // 대기명단 정보 

	@Builder
	public Waiting(Store store, Member host, int standby, int minperson, String meetplace, LocalTime closetime,
			int mincost, String text) {
		this.store = store;
		this.host = host;
		this.standby = standby;
		this.minperson = minperson;
		this.meetplace = meetplace;
		this.closetime = closetime;
		this.mincost = mincost;
		this.text = text;
	}
	
	
	//Default값 설정 
	@PrePersist
    public void prePersist() {
        this.standby = this.standby == 0 ? 1 : this.standby;
        this.minperson = this.minperson == 0 ? 3 : this.minperson;
    }
	
	//대기 인원 추가 
	public void addWaitMem(Member mem) throws ForbiddenException {
		if(this.standby >= this.minperson) {
			throw new ForbiddenException("주문 가능 인원이 초과하였습니다.");
		}
		mem.startWaiting(this);
		waitingmems.add(mem);
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