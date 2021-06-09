package com.distancecalc.DCApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
@ComponentScan
@SpringBootApplication
public class DcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcApplication.class, args);
	}


	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.parse("128KB"));
		factory.setMaxRequestSize(DataSize.parse("128KB"));
		return factory.createMultipartConfig();
	}

}
