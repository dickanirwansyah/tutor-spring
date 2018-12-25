package com.dicka.springbootexamplejsp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.dicka.springbootexamplejsp")
@Import({ConfigurationActiveMQ.class})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureViewResolvers(ViewResolverRegistry resolver){
		InternalResourceViewResolver internalResolver = new InternalResourceViewResolver();
		internalResolver.setPrefix("/WEB-INF/jsp/");
		internalResolver.setSuffix(".jsp");
		resolver.viewResolver(internalResolver);
	}
	
	/** config css js **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry resource){
		resource.addResourceHandler("/static/**")
			.addResourceLocations("/static/");
	}
}
