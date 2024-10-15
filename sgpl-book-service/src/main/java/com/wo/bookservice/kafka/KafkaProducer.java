package com.wo.bookservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String BOOK_TOPIC = "book_topic";

    public void sendFindByIdMessage(String message) {
        kafkaTemplate.send(BOOK_TOPIC, message);
    }

}
