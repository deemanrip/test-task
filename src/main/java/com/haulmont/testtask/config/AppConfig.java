package com.haulmont.testtask.config;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableVaadin
@ComponentScan("com.haulmont.testtask")
public class AppConfig {

}
