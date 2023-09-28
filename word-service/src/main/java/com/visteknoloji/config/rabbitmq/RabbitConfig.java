package com.visteknoloji.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchange);
    }

    @Value("${rabbitmq.saveQueue}")
    private String savePhraseQueue;

    @Value("${rabbitmq.saveKey}")
    private String savePhraseKey;

    @Bean
    public Queue savePhraseQueue(){
        return new Queue(savePhraseQueue);
    }

    @Bean
    public Binding bindingSavePhrase(final Queue savePhraseQueue,final DirectExchange directExchange){
        return BindingBuilder.bind(savePhraseQueue).to(directExchange).with(savePhraseKey);
    }


}
