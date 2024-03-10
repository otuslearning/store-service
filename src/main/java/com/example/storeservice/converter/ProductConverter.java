package com.example.storeservice.converter;

import com.example.storeservice.api.CreateProductRequestDto;
import com.example.storeservice.domain.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductConverter {
    public Product convert(CreateProductRequestDto dto) {
        Product product = new Product();
        product.setProductGuid(dto.getProductGuid());
        product.setRequestGuid(dto.getRequestGuid());
        product.setQuantity(dto.getQuantity());
        return product;
    }

}
