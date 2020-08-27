package io.playdata.themorethebetter;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController 
{
	private final MemberRepository memberRepository;
	
	public MemberController(MemberRepository memberRepository) 
	{
		this.memberRepository = memberRepository;
	}
}