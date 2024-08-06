package vn.likelion.DemoApplication.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
class MessageGenerator {

    public String generateMessage() {
        return "This is a Message.";
    }
}