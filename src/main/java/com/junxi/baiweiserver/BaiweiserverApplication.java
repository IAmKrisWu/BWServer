package com.junxi.baiweiserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.junxi.baiweiserver.mapper")
@EnableCaching
public class BaiweiserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaiweiserverApplication.class, args);
	}
}
