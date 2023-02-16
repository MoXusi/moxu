package com.awx.moxu;

import com.awx.moxu.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.crypto.SecretKey;

@SpringBootApplication
public class MoxuApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MoxuApplication.class, args);
		JwtUtils jwtUtils = run.getBean("jwtUtils", JwtUtils.class);
	}


}
