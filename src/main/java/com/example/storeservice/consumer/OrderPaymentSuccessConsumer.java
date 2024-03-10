package com.example.storeservice.consumer;

import com.example.storeservice.api.ProductNotReservedEvent;
import com.example.storeservice.api.OrderPaymentSuccessEvent;
import com.example.storeservice.api.ProductService;
import com.example.storeservice.api.ProductReservedEvent;
import com.example.storeservice.exception.ReserveNotAvailableException;
import com.example.storeservice.producer.ProductProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPaymentSuccessConsumer {

    private final ObjectMapper mapper;
    private final ProductService productService;
    private final ProductProducer productProducer;
    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topics.order-payment-success}")
    public void consume(String message) {
        OrderPaymentSuccessEvent orderPaymentSuccessEvent = null;
        try {
            orderPaymentSuccessEvent = mapper.readValue(message, OrderPaymentSuccessEvent.class);
            productService.reserveProduct(orderPaymentSuccessEvent.getProductGuid(), orderPaymentSuccessEvent.getQuantity());
            ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                    .productGuid(orderPaymentSuccessEvent.getProductGuid())
                    .date(orderPaymentSuccessEvent.getDate())
                    .orderGuid(orderPaymentSuccessEvent.getOrderGuid())
                    .build();
            productProducer.reservedMessage(productReservedEvent);
        } catch (JsonProcessingException e) {
            log.error("Error parsing message: {}", message, e);
        } catch (ReserveNotAvailableException e) {
            log.error("Error reserve product: {}. {}", message, e.getMessage());
            if(orderPaymentSuccessEvent != null) {
                ProductNotReservedEvent productEvent = ProductNotReservedEvent.builder()
                        .productGuid(orderPaymentSuccessEvent.getProductGuid())
                        .orderGuid(orderPaymentSuccessEvent.getOrderGuid())
                        .build();
                productProducer.notReservedMessage(productEvent);
            }
        }

    }
}
