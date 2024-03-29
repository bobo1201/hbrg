package com.hbrg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableJpaAuditing
@SpringBootApplication
public class HbrgApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbrgApplication.class, args);
	}

}
