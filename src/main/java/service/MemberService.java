package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Member;
import exception.SameMemberException;
import repository.MemberRepository;
import repository.MemoryMemberRepository;

public class MemberService 
{
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) 
	{
		this.memberRepository = memberRepository;
	}
	
	public String join(Member member) // 회원가입
	{
		checkDuplicateId(member); // 중복 회원 검증
		memberRepository.save(member);
		
		return member.getMem_id();
	}
	
	public List<Member> findMembers() // 전체 회원 조회
	{
		return memberRepository.findAll();
	}
	
	public Optional <Member> findOne(String mem_id)
	{
		return memberRepository.findById(mem_id);
	}
	
	private void checkDuplicateId(Member member)
	{
		memberRepository.findById(member.getMem_id()).ifPresent(m -> { // ifPresent : Null이 아니라 어떤 값이 있으면
			throw new SameMemberException();
		});
	}
}