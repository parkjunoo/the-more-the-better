package io.playdata.themorethebetter.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.dto.member.MemberCreateRequestDto;
import io.playdata.themorethebetter.dto.member.MemberLogInRequestDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
	
	@Autowired private MemberService memberService;
	@Autowired private MemberRepository memberRepository;
	
//	@After
//	public void cleanup () {
//		waitingRepository.deleteAll();
//		waitingMemsRepository.deleteAll();
//        hostRepository.deleteAll();
//        memberRepository.deleteAll();
//        classRepository.deleteAll();
//        storeRepository.deleteAll();
//    }
	
	@Test
	public void 회원가입 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.mem_id("ee")
				.mem_pw("ee")
				.mem_name("ee")
				.mem_phone("01055555555")
				.class_code("IP")
				.build();
		
		//when
		memberService.join(dto);
		
		//then
		Member member = memberRepository.findByName("ee").get();
		assertThat(member.getId()).isEqualTo(dto.getMem_id());
		assertThat(member.isIscertify()).isEqualTo(false);
		assertThat(member.getMyclass().getCode()).isEqualTo(dto.getClass_code());
	}
	
	@Test(expected=NotFoundException.class)
	public void 클래스미존재회원가입에러 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.mem_id("ee")
				.mem_pw("ee")
				.mem_name("ee")
				.mem_phone("01055555555")
				.class_code("IP")
				.build();
		
		//when
		memberService.join(dto);
	}
	
	@Test(expected=ForbiddenException.class)
	public void 아이디중복회원가입에러 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.mem_id("aa")
				.mem_pw("ee")
				.mem_name("ee")
				.mem_phone("01055555555")
				.class_code("IP")
				.build();
		
		//when
		memberService.join(dto);
	}
	
	@Test(expected=ForbiddenException.class)
	public void 전화번호중복회원가입에러 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.mem_id("ee")
				.mem_pw("ee")
				.mem_name("ee")
				.mem_phone("01011111111")
				.class_code("IP")
				.build();
		
		//when
		memberService.join(dto);
	}
	
	@Test
	public void 로그인 () {
		//given
		MemberLogInRequestDto dto = MemberLogInRequestDto.builder()
				.mem_id("aa")
				.mem_pw("aa")
				.build();
		
		//when
		Member member = memberService.logIn(dto);
		
		//then 
		assertThat(member.getId()).isEqualTo(dto.getMem_id());
		assertThat(member.getPw()).isEqualTo(dto.getMem_pw());
	}
	
	@Test(expected=NotFoundException.class)
	public void 미존재회원로그인에러 () {
		//given
		MemberLogInRequestDto dto = MemberLogInRequestDto.builder()
				.mem_id("bb")
				.mem_pw("aa")
				.build();
		
		//when
		memberService.logIn(dto);
	}

}
