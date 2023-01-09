package com.erdemnayin.kafka.controller;

import com.erdemnayin.kafka.model.CustomMessage;
import com.erdemnayin.kafka.service.KafkaListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProducerController {

    @Value("${erdemnayin.kafka.topic}")
    private String kafkaTopic;

    private final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/customMessage")
    public void sendMessage(@RequestBody CustomMessage customMessage){
        String kafkaMessageKey = UUID.randomUUID().toString();
//        kafkaTemplate.send("kafka-topic", kafkaMessageKey, customMessage);
//        kafkaTemplate.send(kafkaTopic, kafkaMessageKey, customMessage);

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(kafkaTopic, kafkaMessageKey, customMessage);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=["
                        + customMessage + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                logger.info("Sent message=[" + customMessage +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });

    }

}
