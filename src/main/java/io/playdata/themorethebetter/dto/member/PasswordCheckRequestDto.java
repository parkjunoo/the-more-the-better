/*
 * 멤버 회원가입 DTO 
 *	- membercontroller 에서 memberservice로 갈때 RequestBody로 사용됨 
 */
package io.playdata.themorethebetter.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordCheckRequestDto { 
	private String mem_pw; //비밀번호 
	private String mem_pw_check; //비밀번호 확인 
	
	@Builder
	public PasswordCheckRequestDto(String mem_pw, String mem_pw_check) {
		super();
		this.mem_pw = mem_pw;
		this.mem_pw_check = mem_pw_check;
	}

}
