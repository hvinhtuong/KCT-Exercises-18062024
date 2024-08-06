package vn.likelion.DemoApplication.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class AppConfig {

    @Bean
    @Scope("prototype") // Tạo bean mới
    public MessageGenerator messageGenerator() {
        return new MessageGenerator();
    }
}