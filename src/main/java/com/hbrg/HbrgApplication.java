package com.hbrg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class HbrgApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbrgApplication.class, args);
	}

//	@GetMapping(value = "/")
//	public String HelloWorld(){
//		return "Hello World";
//	}
}
