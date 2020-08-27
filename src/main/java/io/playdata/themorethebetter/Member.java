package io.playdata.themorethebetter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Member 
{
	private @Id @GeneratedValue String id; // 사용자 아이디
	private String name; // 사용자 이름
	private String password; // 사용자 비밀번호
	private String class_code; // 수강 중인 수업 정보가 담긴 코드
	
	public Member(String id, String name, String password, String class_code) 
	{
		this.id = id;
		this.name = name;
		this.password = password;
		this.class_code = class_code;
	}
}	