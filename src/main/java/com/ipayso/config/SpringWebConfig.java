package com.ipayso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * SpringWebConfig.class -> Here we 
 * @author Cleber Oliveira
 * @version 1.0
 * @see @Configuration
 */

@Configuration
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter{
	
    /**
     * Injects LocalValidatorFactoryBean to manager validation
     */
	@Autowired
	private LocalValidatorFactoryBean validator;
	
    /**
     * Make static resources available for all application
     */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
    }
	
	/**
	 * Make the validator factory with messages available
	 */
	@Override
	public Validator getValidator(){
	    return validator;
	}
	
	

	
	
	/*

    private ApplicationContext applicationContext;


    public SpringWebConfig() {
        super();
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("i18n/messages");
    	messageSource.setDefaultEncoding("UTF-8");
    	return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
    	CookieLocaleResolver resolver = new CookieLocaleResolver();
    	resolver.setDefaultLocale(new Locale("en"));
    	resolver.setCookieName("myLocaleCookie");
    	resolver.setCookieMaxAge(4800);
    	return resolver;
    }
    
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addFormatter(dateFormatter());
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    
    /* **************************************************************** */
    /*  THYMELEAF-SPECIFIC ARTIFACTS                                    */
    /*  TemplateResolver <- TemplateEngine <- ViewResolver              */
    /* **************************************************************** */
    
    /**
     * SpringResourceTemplateResolver automatically integrates with Spring's own
     * resource resolution infrastructure, which is highly recommended.
     * HTML is the default value, added on template mode for the sake of clarity.
     * Template cache is true by default. Set to false if you want
     * templates to be automatically updated when modified.
     * @return SpringResourceTemplateResolver
     */
	/*
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("templates/");
        //templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(true);
        return templateResolver;
    }
    
    /**
     * SpringTemplateEngine automatically applies SpringStandardDialect and
     * enables Spring's own MessageSource message resolution mechanisms.
     * @return SpringTemplateEngine
     */
	/*
   @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    
    /**
     * Set a ThymeleafViewResolver in order to manage templates requests
     * @return ThymeleafViewResolver
     */
	/*
    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
    */
}
