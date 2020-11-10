package com.brothers.eslesmeapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.brothers.eslesmeapp.repository"})
@Configuration
@EnableMongoAuditing
public class MongoDbConfig {

}