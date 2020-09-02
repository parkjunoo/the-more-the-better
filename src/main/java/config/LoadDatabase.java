package config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase 
{
	@Bean
	CommandLineRunner initDatabase(repository.MemberRepository memberRepository)
	{
		return args -> {};
	}
}