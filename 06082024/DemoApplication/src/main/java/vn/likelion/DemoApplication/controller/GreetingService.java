package vn.likelion.DemoApplication.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
class GreetingService {

    @PostConstruct // Gọi sau khi bean được khởi tạo
    public void init() {
        System.out.println("GreetingService đã được khởi tạo.");
    }

    @PreDestroy // Gọi trước khi bean bị hủy
    public void destroy() {
        System.out.println("GreetingService sắp bị hủy.");
    }

    public String getGreeting() {
        return "Xin chào, I am Spring Boot!";
    }

    public String getHi() {
        return "Xin chào, anh Ho Vinh Tuong!";
    }
}