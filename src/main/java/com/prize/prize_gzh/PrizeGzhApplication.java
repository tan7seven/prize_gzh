package com.prize.prize_gzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(value="com.prize.prize_gzh.mapper")
public class PrizeGzhApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PrizeGzhApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(PrizeGzhApplication.class, args);
        System.out.println("==》	启动成功");
	}

}

