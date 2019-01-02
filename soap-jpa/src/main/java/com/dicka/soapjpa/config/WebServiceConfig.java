package com.dicka.soapjpa.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/soapWS/*");
    }

    @Bean(name = "person")
    public DefaultWsdl11Definition defaultWsdl11Definition(){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("PersonPort");
        definition.setLocationUri("/soapWS");
        definition.setTargetNamespace("http://myhost.com/soap-jpa/person-ws");
        definition.setSchema(personSchema());
        return definition;
    }

    @Bean
    public XsdSchema personSchema(){
        return new SimpleXsdSchema(new ClassPathResource("person.xsd"));
    }
}
