package io.playdata.themorethebetter.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase 
{
	@Bean
	CommandLineRunner initDatabase(io.playdata.themorethebetter.repository.MemberRepository memberRepository)
	{
		return args -> {};
	}
}