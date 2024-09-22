package com.YunussEmree.buyme;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BuymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuymeApplication.class, args);
	}

	@Bean public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
