package com.visteknoloji.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.saveQueue}")
    private String savePhraseQueue;

    @Bean
    public Queue savePhraseQueue(){
        return new Queue(savePhraseQueue);
    }
}
