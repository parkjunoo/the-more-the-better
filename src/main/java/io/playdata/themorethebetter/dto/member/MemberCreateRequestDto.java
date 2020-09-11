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
	private String mem_id; //아이디 
	private String mem_pw; //비밀번호 
	private String mem_pw_check; //비밀번호 확인 
	private String mem_name; //이름 
	private String mem_phone; //핸드폰번호 
	private String class_code; //클래스
	
	@Builder
	public MemberCreateRequestDto(String mem_id, String mem_pw, String mem_pw_check, String mem_name, String mem_phone,
			String class_code) {
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_pw_check = mem_pw_check;
		this.mem_name = mem_name;
		this.mem_phone = mem_phone;
		this.class_code = class_code;
	}
	
	//클래스 정보 받아서 멤버 생성 
	public Member toEntity(Class myclass) {
		return Member.builder()
				.id(mem_id)
				.pw(mem_pw)
				.name(mem_name)
				.phone(mem_phone)
				.myclass(myclass)
				.build();
	}


}
