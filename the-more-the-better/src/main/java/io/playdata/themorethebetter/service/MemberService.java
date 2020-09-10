package io.playdata.themorethebetter.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.playdata.themorethebetter.domain.Class;
import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.dto.member.MemberCreateRequestDto;
import io.playdata.themorethebetter.dto.member.MemberLogInRequestDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.repository.ClassRepository;
import io.playdata.themorethebetter.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class MemberService {
	
	private MemberRepository memberRepository;
	private ClassRepository classRepository;
	
	/* 회원가입 */
	public Member join(MemberCreateRequestDto dto) throws NotFoundException, ForbiddenException {
		log.info("회원가입 시도중...");
		
		checkExistClass(dto.getClass_code());
		checkDuplicateId(dto.getMem_id());
		checkDuplicatePhone(dto.getMem_phone());
		
		Class myclass = classRepository.findByCode(dto.getClass_code()).get();
		Long mem_no = memberRepository.save(dto.toEntity(myclass)).getNo();
		return memberRepository.findByNo(mem_no).get();
	}
		
	/* 아이디 중복체크 */
	private void checkDuplicateId(String id) throws ForbiddenException{
		log.info("중복 회원 검증");
		
		Optional<Member> member = memberRepository.findById(id);
		
		if(member.isPresent()) {
			throw new ForbiddenException("동일한 아이디가 이미 존재합니다.");
		}	
	}
	
	/* 전화번호 중복체크 */
	private void checkDuplicatePhone(String phone) throws ForbiddenException{
		log.info("중복 전화번호 검증");

		Optional<Member> member = memberRepository.findByPhone(phone);
		
		if(member.isPresent()) {
			throw new ForbiddenException("동일한 전화번호가 이미 존재합니다.");
		}	
	}
	
	/* 클래스 존재여부 확인 */
	private void checkExistClass(String class_code) throws NotFoundException {
		log.info("클래스 존재여부 확인");
		
		classRepository.findByCode(class_code)
			.orElseThrow(() -> new NotFoundException("존재하지 않는 클래스입니다."));
	}
	
	/* 로그인 */
	public Member logIn(MemberLogInRequestDto dto) throws NotFoundException {
		Optional<Member> member = memberRepository.findByIdAndPw(dto.getId(), dto.getPw());
		member.orElseThrow(() -> new NotFoundException());
		
		return member.get();
	}
}