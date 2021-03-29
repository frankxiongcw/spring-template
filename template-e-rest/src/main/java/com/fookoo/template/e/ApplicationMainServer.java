package com.fookoo.template.e;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableScheduling
@MapperScan(value = {"com.fookoo.template.server.dao"})
public class ApplicationMainServer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMainServer.class, args);
	}

}
