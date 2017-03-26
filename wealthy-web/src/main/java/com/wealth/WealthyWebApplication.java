package com.wealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.wealth")
public class WealthyWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WealthyWebApplication.class, args);
	}
}
