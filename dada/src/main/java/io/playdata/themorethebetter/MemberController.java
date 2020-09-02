package io.playdata.themorethebetter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.playdata.themorethebetter.exception.MemberNotFoundException;

@RestController
public class MemberController 
{
	private final MemberRepository memberRepository;
	
	public MemberController(MemberRepository memberRepository) 
	{
		this.memberRepository = memberRepository;
	}
	
	@GetMapping("/member")
	List<Member> all()
	{
		return memberRepository.findAll();
	}
	
	@PostMapping("/member")
	Member newMember(@RequestBody Member newMember)
	{
		return memberRepository.save(newMember);
	}
}