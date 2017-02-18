package com.ipayso.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * RepositoryConfiguration.class -> Here @Configuraion allows the container scan Entities on package which the models are
 * 									as well repositories on our package to be used by Spring as JPA,
 * 									them make Spring coordinate the transactions
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Configuration
 * @see @EnableAutoConfiguration
 * @see @EntityScan
 * @see @EnableJpaRepositories
 * @see @EnableTransactionManagement
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ipayso.model"})
@EnableJpaRepositories(basePackages = {"com.ipayso.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
	
}