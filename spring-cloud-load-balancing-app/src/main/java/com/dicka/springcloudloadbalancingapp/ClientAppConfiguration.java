package com.dicka.springcloudloadbalancingapp;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class ClientAppConfiguration {

    @Autowired
    private IClientConfig clientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig iClientConfig){
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig iClientConfig){
        return new WeightedResponseTimeRule();
    }
}
