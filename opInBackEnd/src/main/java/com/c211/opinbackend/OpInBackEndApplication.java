package com.c211.opinbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:properties/env.properties")
public class OpInBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpInBackEndApplication.class, args);
	}

}
