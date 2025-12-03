//package com.mallikarjun.portfolios.config;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//
//@Configuration
//public class MongoConfig {
//
//    @Bean
//    public MongoClient reactiveMongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//        return MongoClients.create(settings);
//    }
//
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient client) {
//        return new ReactiveMongoTemplate(client, "portfolio");
//    }
//}
//
