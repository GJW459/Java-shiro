package com.gjw.shirospringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gjw.shirospringboot.dao")
public class ShirospringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirospringbootApplication.class, args);
    }

}
