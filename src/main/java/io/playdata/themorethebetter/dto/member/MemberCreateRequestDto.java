/*
 * 멤버 회원가입 DTO 
 *	- membercontroller 에서 memberservice로 갈때 RequestBody로 사용됨 
 */
package io.playdata.themorethebetter.dto.member;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Class;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberCreateRequestDto {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private Long class_no;
	
	@Builder
	public MemberCreateRequestDto(String id, String pw, String name, String phone, Long class_no) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.class_no = class_no;
	}
	
	public Member toEntity(Class myclass) {
		return Member.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.phone(phone)
				.myclass(myclass)
				.build();
	}	
}
