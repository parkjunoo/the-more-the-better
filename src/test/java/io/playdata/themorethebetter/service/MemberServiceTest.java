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
import io.playdata.themorethebetter.repository.ClassRepository;
import io.playdata.themorethebetter.repository.HostRepository;
import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.StoreRepository;
import io.playdata.themorethebetter.repository.WaitingMemsRepository;
import io.playdata.themorethebetter.repository.WaitingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
	
	@Autowired private MemberService memberService;
	@Autowired private WaitingRepository waitingRepository;
	@Autowired private ClassRepository classRepository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private StoreRepository storeRepository;
	@Autowired private WaitingMemsRepository waitingMemsRepository;
	@Autowired private HostRepository hostRepository;	
	
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
				.id("ee")
				.pw("ee")
				.name("ee")
				.phone("01055555555")
				.class_no((long)1)
				.build();
		
		//when
		memberService.join(dto);
		
		//then
		Member member = memberRepository.findByName("ee").get();
		assertThat(member.getId()).isEqualTo(dto.getId());
		assertThat(member.isIscertify()).isEqualTo(false);
		assertThat(member.getMyclass().getNo()).isEqualTo(dto.getClass_no());
	}
	
	@Test(expected=NotFoundException.class)
	public void 클래스미존재회원가입에러 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.id("ee")
				.pw("ee")
				.name("ee")
				.phone("01055555555")
				.class_no((long)6)
				.build();
		
		//when
		memberService.join(dto);
	}
	
	@Test(expected=ForbiddenException.class)
	public void 아이디중복회원가입에러 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.id("aa")
				.pw("ee")
				.name("ee")
				.phone("01055555555")
				.class_no((long)1)
				.build();
		
		//when
		memberService.join(dto);
	}
	
	@Test(expected=ForbiddenException.class)
	public void 전화번호중복회원가입에러 () {
		//given
		MemberCreateRequestDto dto = MemberCreateRequestDto.builder()
				.id("ee")
				.pw("ee")
				.name("ee")
				.phone("01011111111")
				.class_no((long)1)
				.build();
		
		//when
		memberService.join(dto);
	}
	
	@Test
	public void 로그인 () {
		//given
		MemberLogInRequestDto dto = MemberLogInRequestDto.builder()
				.id("aa")
				.pw("aa")
				.build();
		
		//when
		Member member = memberService.logIn(dto);
		
		//then 
		assertThat(member.getId()).isEqualTo(dto.getId());
		assertThat(member.getPw()).isEqualTo(dto.getPw());
	}
	
	@Test(expected=NotFoundException.class)
	public void 미존재회원로그인에러 () {
		//given
		MemberLogInRequestDto dto = MemberLogInRequestDto.builder()
				.id("bb")
				.pw("aa")
				.build();
		
		//when
		memberService.logIn(dto);
	}

}
