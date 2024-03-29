package com.bits.epm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpmApplication.class, args);
	}

}
