package controller;

import dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/send")
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC_NAME = "Test_Topic";

    @PostMapping
    public ResponseEntity<String> sendMsg(@RequestBody MessageDto message) {
        try {
            kafkaTemplate.send(TOPIC_NAME, message.getKey(), message.getValue());
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
