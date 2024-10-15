package com.wo.bookservice.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wo.bookservice.kafka.BookResponse;
import com.wo.bookservice.kafka.KafkaProducer;
import com.wo.bookservice.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private static final Log LOG = LogFactory.getLog(KafkaConsumer.class);

    @KafkaListener(topics = "reservation_topic", groupId = "reservation-group")
    public void clientConsumer(String message) {
        System.out.println("\nReceived message from CLIENT_TOPIC: " + message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Long id2 = mapper.readValue(message, Long.class);
            findByIdMessage(id2);
            LOG.info(infoBook(id2));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private final KafkaProducer kafkaProducer;
    private final IBookService bookService;

    public ResponseEntity<?> findByIdMessage(Long id) throws JsonProcessingException {
        BookResponse book = bookService.bookById(id);

        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(book);

        kafkaProducer.sendFindByIdMessage(message);

        return ResponseEntity.ok("Successfully, message send to KafkaClient Producer: " + message);
    }

    private static String infoBook(Long response) {
        return String.format("""
                
                INFO CLIENT: \
                
                Id: %s\
                
                Name: %s""", response);
    }

}
