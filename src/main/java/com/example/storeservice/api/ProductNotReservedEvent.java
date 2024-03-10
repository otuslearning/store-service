package com.example.storeservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductNotReservedEvent {
    private String productGuid;
    private String orderGuid;
}
