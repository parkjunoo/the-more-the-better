package io.playdata.themorethebetter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.domain.Class;
import io.playdata.themorethebetter.dto.member.MemberCreateRequestDto;
import io.playdata.themorethebetter.dto.member.MemberLogInRequestDto;
import io.playdata.themorethebetter.exception.ForbiddenException;
import io.playdata.themorethebetter.exception.NotFoundException;
import io.playdata.themorethebetter.exception.SameMemberException;
import io.playdata.themorethebetter.repository.ClassRepository;
import io.playdata.themorethebetter.repository.HostRepository;
import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.StoreRepository;
import io.playdata.themorethebetter.repository.WaitingMemsRepository;
import io.playdata.themorethebetter.repository.WaitingRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	private ClassRepository classRepository;
	
	/* 회원가입 */
	public Member join(MemberCreateRequestDto dto) throws NotFoundException, ForbiddenException {
		
		checkExistClass(dto.getClass_no()); // 클래스 존재여부 확인 
		checkDuplicateId(dto.getId()); // 중복 회원 검증
		checkDuplicatePhone(dto.getPhone()); // 중복 전화번호 검증
		
		Class myclass = classRepository.findByNo(dto.getClass_no()).get();
		Long mem_no = memberRepository.save(dto.toEntity(myclass)).getNo();
		return memberRepository.findByNo(mem_no).get();
	}
		
	/* 아이디 중복체크 */
	private void checkDuplicateId(String id) throws ForbiddenException{
		Optional<Member> member = memberRepository.findById(id);
		
		if(member.isPresent()) {
			throw new ForbiddenException("동일한 아이디가 이미 존재합니다.");
		}	
	}
	
	/* 전화번호 중복체크 */
	private void checkDuplicatePhone(String phone) throws ForbiddenException{
		Optional<Member> member = memberRepository.findByPhone(phone);
		
		if(member.isPresent()) {
			throw new ForbiddenException("동일한 전화번호가 이미 존재합니다.");
		}	
	}
	
	/* 클래스 존재여부 확인 */
	private void checkExistClass(Long class_no) throws NotFoundException {
		classRepository.findByNo(class_no)
			.orElseThrow(() -> new NotFoundException("존재하지 않는 클래스입니다."));
	}
	
	/* 로그인 */
	public Member logIn(MemberLogInRequestDto dto) throws NotFoundException {
		Optional<Member> member = memberRepository.findByIdAndPw(dto.getId(), dto.getPw());
		member.orElseThrow(() -> new NotFoundException());
		
		return member.get();
	}
}