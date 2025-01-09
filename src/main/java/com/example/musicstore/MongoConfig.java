package com.example.musicstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/music-store-db"));
    }
}