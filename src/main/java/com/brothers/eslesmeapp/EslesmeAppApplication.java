package com.brothers.eslesmeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MongoDbConfig.class)
public class EslesmeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EslesmeAppApplication.class, args);
	}

}
