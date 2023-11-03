package com.springboot;

import com.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

@Autowired
private UserMapper userMapper;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}



}
