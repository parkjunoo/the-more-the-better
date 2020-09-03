package io.playdata.themorethebetter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.playdata.themorethebetter.repository.MemberRepository;
import io.playdata.themorethebetter.repository.MemoryMemberRepository;
import io.playdata.themorethebetter.service.MemberService;

@Configuration // Component 등록
public class SpringConfig
{
	@Bean
	public MemberService memberService()
	{
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository()
	{
		return new MemoryMemberRepository();
	}
}