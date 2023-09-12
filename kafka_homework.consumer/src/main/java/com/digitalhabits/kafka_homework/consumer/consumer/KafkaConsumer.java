package com.digitalhabits.kafka_homework.consumer.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private static final String TOPIC_NAME = "Test_Topic";

    @KafkaListener(topics = TOPIC_NAME, containerFactory = "kafkaListenerContainerFactory")
    public void KafkaListener(@Payload String message,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic) {
        log.info("Receive message from Topic {}: {}", receivedTopic, message);
    }
}