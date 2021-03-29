package com.fookoo.template.c;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
@ComponentScan(value = "com.fookoo.template")
@MapperScan(value = {"com.fookoo.template.server.dao"})
public class ApplicationMainClient {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMainClient.class, args);
	}

}
