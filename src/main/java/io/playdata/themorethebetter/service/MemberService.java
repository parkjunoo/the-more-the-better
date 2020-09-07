package io.playdata.themorethebetter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.playdata.themorethebetter.domain.Member;
import io.playdata.themorethebetter.exception.SameMemberException;
import io.playdata.themorethebetter.repository.MemberRepository;

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
		
		return member.getId();
	}
	
	public List<Member> findMembers() // 전체 회원 조회
	{
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(String mem_id)
	{
		return memberRepository.findById(mem_id);
	}
	
	private void checkDuplicateId(Member member)
	{
		memberRepository.findById(member.getId()).orElseThrow(() -> new SameMemberException());
	}
}