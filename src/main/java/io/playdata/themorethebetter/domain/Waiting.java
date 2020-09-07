package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.ColumnDefault;

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
	@Column(name="WAIT_MINLIMIT")
	private Long minlimit; // 주문 가능한 최소 인원
	
	@OneToOne(optional=false)
	@JoinColumn(name="WAIT_MEM_NO")
	private WaitingMems waitingmems; // 대기명단 정보 

	@Builder
	public Waiting(Store store, Host host, Long minlimit, WaitingMems waitingmems) {
		this.store = store;
		this.host = host;
		this.minlimit = minlimit;
		this.waitingmems = waitingmems;
	}
	
	//Default값 설정 
	@PrePersist
    public void prePersist() {
        this.standby = this.standby == null ? 1 : this.standby;
        this.minlimit = this.minlimit == null ? 3 : this.minlimit;
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