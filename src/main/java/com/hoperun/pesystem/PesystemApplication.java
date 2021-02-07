package com.hoperun.pesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@EnableAsync
@MapperScan("com.hoperun.pesystem.mapper")
@SpringBootApplication
public class PesystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PesystemApplication.class, args);
    }

}
