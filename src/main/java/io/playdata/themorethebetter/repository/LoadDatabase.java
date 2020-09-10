package io.playdata.themorethebetter.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.playdata.themorethebetter.domain.Class;
import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class LoadDatabase 
{
	@Bean
	CommandLineRunner initDatabase(MemberRepository memberRepository, ClassRepository classRepository, StoreRepository storeRepository)
	{
		return args -> {
			/* Class 생성 */
			log.info("Preloading" + classRepository.save(Class.builder().code("IP").name("AI Pose").build()));
			log.info("Preloading" + classRepository.save(Class.builder().code("IA").name("AI Automobile").build()));
			log.info("Preloading" + classRepository.save(Class.builder().code("DSY").name("Data Science Yeongdeungpo").build()));
			log.info("Preloading" + classRepository.save(Class.builder().code("DSS").name("Data Science Seocho").build()));
		};
	}
}