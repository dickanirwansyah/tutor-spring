package com.dicka.springbootexamplejsp.message;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.dicka.springbootexamplejsp.entity.InventoryResponse;
import com.dicka.springbootexamplejsp.service.OrderService;

@Component
public class MessageReceiver {

	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";
	private final OrderService orderService;
	
	@Autowired
	public MessageReceiver(OrderService orderService){
		this.orderService = orderService;
	}
	
	@JmsListener(destination = ORDER_RESPONSE_QUEUE)
	public void receiveMessage(final Message<InventoryResponse> message) throws JMSException{
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Application : headers received : {}", headers);
		
		InventoryResponse response = message.getPayload();
		LOG.info("Application : response received : {}",response);
		
		orderService.updateOrder(response);	
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
}
