package com.dicka.customerservice;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.dicka.customerservice")
public class CustomerConfiguration {

	@Bean
	public AlwaysSampler alwaysSampler(){
		return new AlwaysSampler();
	}
	
}
