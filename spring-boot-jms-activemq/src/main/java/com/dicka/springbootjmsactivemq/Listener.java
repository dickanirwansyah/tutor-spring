package com.dicka.springbootjmsactivemq;

import com.google.gson.Gson;
import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class Listener {


    @JmsListener(destination = "inbound.queue")
    @SendTo("outbound.queue")
    public String receiveMessage(final Message jsonMessage) throws JMSException{
        String messageData = null;
        /** test **/
        System.out.println("Received Message : "+jsonMessage);
        String response = null;
        if (jsonMessage instanceof TextMessage){
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            Map map = new Gson()
                    .fromJson(messageData, Map.class);
            response = "Hallo --> "+map.get("name");
        }
        return response;
    }

    @JmsListener(destination = "inbound.topic")
    @SendTo("outbound.topic")
    public String receiveMessageFromTopic(final Message jsonMessage) throws JMSException{
        String messageData = null;
        System.out.println("Received Message : "+jsonMessage);
        String response = null;
        if (jsonMessage instanceof TextMessage){
            TextMessage textMessage = (TextMessage) jsonMessage;
            messageData = textMessage.getText();
            Map map = new Gson()
                    .fromJson(messageData, Map.class);
            response = "Hallo --> "+map.get("name");
        }
        return response;
    }
}
