package io.playdata.themorethebetter.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity
{
	@Id @GeneratedValue
	@Column(name="MEM_NO")
	private Long no; // 사용자 고유번호	
	
	@Column(name="MEM_ID", length=20, nullable=false)
	private String id; // 사용자 아이디
	
	@Column(name="MEM_PW", length=20, nullable=false)
	private String pw; // 사용자 비밀번호
	
	@Column(name="MEM_NAME", length=20)
	private String name; // 사용자 이름
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CLASS_NO")
	private Class myclass; // 수강 중인 수업 정보 
	
	@Column(name="MEM_ISCERTIFY", columnDefinition="number(1,0) default 0")
	private boolean iscertify; // 회원 인증 여부 
	
	@OneToOne(mappedBy="member")
	private Host host; // 사용자의 주최자 정보 
	
	@ManyToOne
	@JoinColumn(name="WAIT_MEM_NO")
	private WaitingMems waitingmems; //속해있는 대기명단 정보 

	@Builder
	public Member(String id, String pw, String name, Class myclass, boolean iscertify, Host host, WaitingMems waitingmems) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.myclass = myclass;
		this.iscertify = iscertify;
		this.host = host;
		this.waitingmems = waitingmems;
	}
	
	//대기 시작 
	public void startWaiting(WaitingMems waitingmems) {
		this.waitingmems = waitingmems;
	}
	
	//대기 취소 
	public void cancelWaiting() {
		this.waitingmems = null;
	}

}	