package com.wenze.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@MapperScan("com.wenze.security.mapper")
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurity01HelloworldApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurity01HelloworldApplication.class, args);
  }

}
