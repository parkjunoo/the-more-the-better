package io.playdata.themorethebetter.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.playdata.themorethebetter.MemberRepository;

@Configuration
public class LoadDatabase 
{
	@Bean
	CommandLineRunner initDatabase(MemberRepository memberRepository)
	{
		return args -> {};
	}
}