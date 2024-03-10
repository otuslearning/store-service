package com.example.storeservice.producer;

import com.example.storeservice.api.ProductNotReservedEvent;
import com.example.storeservice.api.ProductReservedEvent;
import com.example.storeservice.exception.ConvertException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductProducer {
    private final ObjectMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void reservedMessage(ProductReservedEvent message){
        Assert.notNull(message, "message mustn't be null");
        try {
            kafkaTemplate.send("product-reserved", mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            log.error("Error convert and send product reserved event: {}", message, e);
            throw new ConvertException(e.getMessage());
        }
    }

    public void notReservedMessage(ProductNotReservedEvent message) {
        Assert.notNull(message, "message mustn't be null");
        try {
            kafkaTemplate.send("product-not-reserved", mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            log.error("Error convert and send product not reserved event: {}", message, e);
            throw new ConvertException(e.getMessage());
        }
    }
}
