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
public class MemberLogInRequestDto {
	private String mem_id;
	private String mem_pw;
	
	@Builder
	public MemberLogInRequestDto(String mem_id, String mem_pw) {
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
	}
	
}
