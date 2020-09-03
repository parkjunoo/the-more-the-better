package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.exception.SameMemberException;
import io.playdata.themorethebetter.repository.MemoryMemberRepository;
import io.playdata.themorethebetter.service.MemberService;

public class MemberServiceTest 
{
	MemberService memberService;
	MemoryMemberRepository memberRepository; // repository 클리어를 위하여 선언함
	
	@BeforeEach // 동작하기 전
	public void beforeEach()
	{
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() // repository 클리어 부분
	{
		memberRepository.clearStore();
	}
	
	@Test
	public void join() // 기본 가입 테스트
	{
		Member member = new Member();
		member.setMem_id("eplesky");
		
		String saveId = memberService.join(member);
		
		Member findMember = memberService.findOne(saveId).get();
		
		assertThat(findMember.getMem_id()).isEqualTo(findMember.getMem_id());
	}
	
	@Test
	public void checkDuplicateId() // 중복 가입 테스트
	{
		Member member1 = new Member();
		member1.setMem_id("eplesky");
		
		Member member2 = new Member();
		member2.setMem_id("eplesky");
		
		memberService.join(member1);
		
		assertThrows(SameMemberException.class, () -> memberService.join(member2));
	}
	
	@Test
	public void findMembers()
	{
		
	}
	
	@Test
	public void findOne()
	{
		
	}
}