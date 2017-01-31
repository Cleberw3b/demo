package com.ipayso.local.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ipayso.local.model"})
@EnableJpaRepositories(basePackages = {"com.ipayso.local.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
	
}