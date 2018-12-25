package com.dicka.springbootexamplejsp.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.dicka.springbootexamplejsp.entity.Order;

@Component
public class MessageSender {

	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public MessageSender(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessage(final Order order){
		jmsTemplate.send(new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objMessage = session.createObjectMessage(order);
				return objMessage;
			}
		});
	}
}
