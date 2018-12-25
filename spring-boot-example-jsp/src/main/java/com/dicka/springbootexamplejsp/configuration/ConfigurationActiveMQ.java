package com.dicka.springbootexamplejsp.configuration;

import java.util.Arrays;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ConfigurationActiveMQ {
	
	//message broker url 
	private static final String BROKER_URL = "tcp://localhost:61616";
	//queue atau antrian order
	private static final String ORDER_QUEUE = "order-queue";
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		ActiveMQConnectionFactory activeFactory = new ActiveMQConnectionFactory();
		activeFactory.setBrokerURL(BROKER_URL);
		activeFactory.setUserName("admin");
		activeFactory.setPassword("admin");
		activeFactory.setTrustedPackages(Arrays.asList("com.dicka.springbootexamplejsp"));
		return activeFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_QUEUE);
		return template;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerFactory(){
		DefaultJmsListenerContainerFactory listenerFactory = new DefaultJmsListenerContainerFactory();
		listenerFactory.setConnectionFactory(connectionFactory());
		listenerFactory.setConcurrency("1-1");
		return listenerFactory;
	}
}
