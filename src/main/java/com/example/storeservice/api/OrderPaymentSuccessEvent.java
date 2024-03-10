package com.example.storeservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderPaymentSuccessEvent {
    private String orderGuid;
    private String productGuid;
    private String date;
    private Integer quantity;
}
