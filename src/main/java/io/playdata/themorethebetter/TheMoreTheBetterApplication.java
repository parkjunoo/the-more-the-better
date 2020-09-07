package io.playdata.themorethebetter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TheMoreTheBetterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheMoreTheBetterApplication.class, args);
	}

}
