package com.example.iredms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
//@PropertySource("file:.env")
@ComponentScan(basePackages = {"com.huawei.innovation", "com.example.iredms"})
public class IreDmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IreDmsApplication.class, args);
    }

}
