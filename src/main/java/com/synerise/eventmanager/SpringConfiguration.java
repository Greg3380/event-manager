package com.synerise.eventmanager;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "com.synerise.eventmanager")
@EnableScheduling
public class SpringConfiguration {
}