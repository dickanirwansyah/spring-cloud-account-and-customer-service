package com.dicka.accountservice;

import org.springframework.context.annotation.Bean;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.dicka.accountservice")
public class ConfigurationAccount {

	@Bean
	public AlwaysSampler alwaysSample(){
		return new AlwaysSampler();
	}
}
