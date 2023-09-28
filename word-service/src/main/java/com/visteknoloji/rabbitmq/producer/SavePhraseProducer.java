package com.visteknoloji.rabbitmq.producer;

import com.visteknoloji.rabbitmq.model.SavePhraseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePhraseProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.saveKey}")
    private String savePhraseKey;

    public void convertAndSendData(SavePhraseModel model){
        rabbitTemplate.convertAndSend(exchange,savePhraseKey,model);
    }
}
