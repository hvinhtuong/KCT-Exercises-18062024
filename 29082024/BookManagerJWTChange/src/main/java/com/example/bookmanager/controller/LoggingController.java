package com.example.bookmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class LoggingController {

    private static final Logger logger = LogManager.getLogger(LoggingController.class);

    @GetMapping("/log")
    public String logExample() {
        logger.info("Info level Log4j");
        logger.debug("Debug level Log4j");
        logger.warn("Warning level Log4j");
        logger.error("Error level Log4j", new Exception("Example exception"));
        return "Logging has been demonstrated. Check the console and the log file!";
    }
}
