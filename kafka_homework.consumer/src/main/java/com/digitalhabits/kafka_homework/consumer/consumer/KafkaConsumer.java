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

    private static final String KAFKA_TOPIC = "Topic";

    @KafkaListener(topics = KAFKA_TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public void KafkaListener(@Payload String message,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic,
                              @Header(KafkaHeaders.RECEIVED_PARTITION) String receivedPartition,
                              @Header(KafkaHeaders.OFFSET) String receivedOffset) {
        log.info("Receive from Topic {} by partition {}, offset {}: {}", receivedTopic, receivedPartition, receivedOffset, message);
    }
}