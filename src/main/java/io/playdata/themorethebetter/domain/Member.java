package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	
	@Column(name="MEM_PHONE", length=20)
	private String phone; // 사용자 전화번호 
	
	@JsonIgnore
	@ManyToOne(optional=false)
	@JoinColumn(name="CLASS_NO")
	private Class myclass; // 수강 중인 수업 정보 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="WAIT_NO")
	private Waiting mywait; //속해있는 주문 정보  
	
	@Column(name="MEM_ISCERTIFY", columnDefinition="number(1,0) default 0")
	private boolean iscertify; // 회원 인증 여부 
	
	@Column(name="MEM_ISHOST", columnDefinition="number(1,0) default 0")
	private boolean ishost; // 사용자의 주최자 여부  
	
	@Builder
	public Member(String id, String pw, String name, String phone, Class myclass, Waiting mywait, boolean iscertify,
			boolean ishost) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.myclass = myclass;
		this.mywait = mywait;
		this.iscertify = iscertify;
		this.ishost = ishost;
	}
	
	//호스트 권한 부여 
	public void createHost() throws ForbiddenException {
		if(this.ishost) {
			throw new ForbiddenException("이미 다른 주문이 진행중입니다.");
		}
		this.ishost = true;
	}
	
	//호스트 권한 박탈 
	public void deleteHost() throws ForbiddenException {
		if(!this.ishost) {
			throw new ForbiddenException("현재 진행중이 주문이 없습니다.");
		}
		this.ishost = false;
	}
	
	//주문 대기 참여 
	public void startWaiting(Waiting wait) throws ForbiddenException {
		if(this.mywait != null) {
			throw new ForbiddenException("이미 참여중인 주문이 존재합니다.");
		}
		this.mywait = wait;
	}
	
	//대기중인 주문 취소 
	public void cancelWaiting() throws ForbiddenException {
		if(this.mywait == null) {
			throw new ForbiddenException("참여중인 주문이 존재하지 않습니다.");
		}
		this.mywait = null;
	}
	
	//참여중인 주문 검색 
	public Waiting searchWaiting() throws NotFoundException {
		if(this.mywait == null) {
			throw new NotFoundException("참여중인 주문이 존재하지 않습니다.");
		}
		return this.mywait;
	}
}	