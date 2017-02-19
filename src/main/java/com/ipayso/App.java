package com.ipayso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * App.class -> Here is where Spring start the application the public static void main runs it as
 * 				a servlet application provided by SpringApplicationBuilder.
 * @author Cleber Oliveira
 * @version 1.0
 * @see SpringBootServletInitializer
 * @see SpringApplicationBuilder
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<App> applicationClass = App.class;
}
