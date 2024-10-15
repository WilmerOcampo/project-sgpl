package com.wo.bookservice.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Log LOG = LogFactory.getLog(KafkaConsumer.class);

    @KafkaListener(topics = "reservation_topic", groupId = "reservation-group")
    public void clientConsumer(String message) {
        System.out.println("\nReceived message from CLIENT_TOPIC: " + message);
        ObjectMapper mapper = new ObjectMapper();
        try {
            Long id2 = mapper.readValue(message, Long.class);
            LOG.info(infoBook(id2));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String infoBook(Long response) {
        return String.format("""
                
                INFO CLIENT: \
                
                Id: %s\
                
                Name: %s""", response);
    }

}
