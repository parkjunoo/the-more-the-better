package io.playdata.themorethebetter.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Class extends BaseTimeEntity
{
	@Id @GeneratedValue(strategy= GenerationType.AUTO,generator="native3")
	@Column(name="CLASS_NO")
	private Long no; // 수업 고유번호	
	
	@Column(name="CLASS_NAME", length=40)
	private String name; // 수업 이름
	
	@Column(name="CLASS_CODE", length=4, nullable=false)
	private String code; // 수업 코드 
	
	@JsonIgnore
	@OneToMany(mappedBy="myclass")
	private List<Member> members = new ArrayList<Member>(); // 수강중인 사용자 정보 

	@Builder
	public Class(String name, String code, List<Member> members) {
		this.name = name;
		this.code = code;
		this.members = members;
	}
}	