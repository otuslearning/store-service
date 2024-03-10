package com.example.storeservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductReservedEvent {
    private String productGuid;
    private String orderGuid;
    private Integer quantity;
    private String date;
}
