package com.erdemnayin.kafka.service;

import com.erdemnayin.kafka.model.CustomMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    private final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

//    @KafkaListener(topics = "kafkaTopic", groupId = "consumer-group")
    @KafkaListener(
            topics = "${erdemnayin.kafka.topic}",
            groupId = "${erdemnayin.kafka.group.id}"
    )
    public void consume(CustomMessage customMessage){
        logger.info(String.format("Message received: %s", customMessage));
    }
}
