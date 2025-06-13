package com.salesflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.salesflow")
@EntityScan(basePackages = "com.salesflow.adapter.repository.entity")
@EnableJpaRepositories(basePackages = "com.salesflow.adapter.repository")
@EnableAsync
@EnableScheduling
public class SalesFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesFlowApplication.class, args);
	}
}