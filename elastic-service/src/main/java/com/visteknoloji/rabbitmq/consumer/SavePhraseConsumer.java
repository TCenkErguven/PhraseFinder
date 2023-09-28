package com.visteknoloji.rabbitmq.consumer;


import com.visteknoloji.rabbitmq.model.SavePhraseModel;
import com.visteknoloji.service.WordHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePhraseConsumer {

    private final WordHolderService wordHolderService;

    @RabbitListener(queues = ("${rabbitmq.saveQueue}"))
    public void savePhrase(SavePhraseModel model){
        wordHolderService.saveWordHolder(model);
    }
}
