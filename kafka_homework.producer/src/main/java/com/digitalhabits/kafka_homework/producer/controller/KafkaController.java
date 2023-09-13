package com.digitalhabits.kafka_homework.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/send")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC_NAME = "Test_Topic";

    @PostMapping
    public ResponseEntity<String> sendMsg(@RequestParam String message) {
        try {
            kafkaTemplate.send(TOPIC_NAME, message);
            String info = "Сообщение успешно отправлено";
            log.info("Сообщение успешно отправлено");
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            String error = "Произошла ошибка при отправке сообщения";
            log.error("Произошла ошибка при отправке сообщения");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
