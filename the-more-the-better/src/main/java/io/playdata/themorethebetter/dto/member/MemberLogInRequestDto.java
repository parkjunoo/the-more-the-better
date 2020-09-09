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
	private String id;
	private String pw;
	
	@Builder
	public MemberLogInRequestDto(String id, String pw) {
		super();
		this.id = id; 
		this.pw = pw;
	}
}
