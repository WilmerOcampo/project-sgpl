package com.wo.reservationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Log LOG = LogFactory.getLog(KafkaConsumer.class);

    @KafkaListener(topics = "book_topic", groupId = "book-group")
    public void clientConsumer(String message) {
        System.out.println("\nReceived message from CLIENT_TOPIC: " + message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            BookResponse bookResponse = mapper.readValue(message, BookResponse.class);
            LOG.info(infoBook(bookResponse));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String infoBook(BookResponse response) {
        return String.format("""
                
                INFO CLIENT: \
                
                Id: %s\
                
                Name: %s""", response.id(), response.title());
    }

}
