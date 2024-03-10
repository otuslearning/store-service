package com.example.storeservice.web;

import com.example.storeservice.api.CreateProductRequestDto;
import com.example.storeservice.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.web.prefix.public}/products")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addProduct(@RequestBody CreateProductRequestDto dto) {
        productService.addProduct(dto);
    }

}
