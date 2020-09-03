package io.playdata.themorethebetter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Member 
{
	@Id @GeneratedValue
	@Column(name="MEM_NO")
	private Long mem_no; // 사용자 고유번호	
	
	@Column(length=20, nullable=false)
	private String mem_id; // 사용자 아이디
	
	@Column(length=20, nullable=false)
	private String mem_pw; // 사용자 비밀번호
	
	@Column(length=20)
	private String mem_name; // 사용자 이름
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CLASS_NO")
	private Class mem_class; // 수강 중인 수업 정보 
	
	@Column(columnDefinition="number(1,0) default 0")
	private boolean mem_iscertify; // 회원 인증 여부 
	
	@OneToOne(mappedBy="host_member")
	private Host mem_host; // 사용자의 주최자 정보 
	
	@ManyToOne
	@JoinColumn(name="WAIT_MEM_NO")
	private WaitingMems mem_wait; //속해있는 대기명단 정보 
	
}	