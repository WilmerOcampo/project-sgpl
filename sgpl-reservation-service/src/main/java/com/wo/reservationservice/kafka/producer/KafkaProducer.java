package com.wo.reservationservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String BOOK_TOPIC = "reservation_topic";

    public void sendIdMessage(String message) {
        kafkaTemplate.send(BOOK_TOPIC, message);
    }

}
