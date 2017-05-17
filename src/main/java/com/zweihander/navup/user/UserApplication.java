package com.zweihander.navup.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@EnableDiscoveryClient
//@SpringBootApplication
@ComponentScan("com.zweihander.navup.user")
public class UserApplication {

	public static void main(String[] args){
		SpringApplication.run(UserApplication.class, args);
	}
}