package com.ddinhftieens.demo_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCrudApplication.class, args);
    }

    @Bean(name = "demo")
    public String getString(){
        return "demoBean";
    }
}
