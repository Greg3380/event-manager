package com.synerise.eventmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EventManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }

}
