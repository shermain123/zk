package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ZkEuerkaServer {

	public static void main(String[] args) {
		
		SpringApplication.run(ZkEuerkaServer.class, args);
	}

}
