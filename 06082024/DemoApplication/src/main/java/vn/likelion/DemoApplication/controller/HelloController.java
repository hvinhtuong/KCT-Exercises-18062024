package vn.likelion.DemoApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @Autowired
    // Tiêm GreetingService vào
    private GreetingService greetingService;

    @GetMapping("/greet")
    public String hello() {
        return greetingService.getGreeting();
    }

    @GetMapping("/greet1")
    public String hi() {
        return greetingService.getHi();
    }
}