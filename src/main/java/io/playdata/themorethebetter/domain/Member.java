package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CLASS_NO")
	private Class myclass; // 수강 중인 수업 정보 
	
	@Column(name="MEM_ISCERTIFY", columnDefinition="number(1,0) default 0")
	private boolean iscertify; // 회원 인증 여부 

	@Builder
	public Member(String id, String pw, String name, String phone, Class myclass, boolean iscertify) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.myclass = myclass;
		this.iscertify = iscertify;
	}

}	