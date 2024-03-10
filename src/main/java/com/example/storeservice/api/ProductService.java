package com.example.storeservice.api;

import com.example.storeservice.exception.ReserveNotAvailableException;

public interface ProductService {
    void addProduct(CreateProductRequestDto dto);
    void reserveProduct(String productGuid, Integer quantity) throws ReserveNotAvailableException;
}
