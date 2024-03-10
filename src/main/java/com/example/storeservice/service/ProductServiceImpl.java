package com.example.storeservice.service;

import com.example.storeservice.api.CreateProductRequestDto;
import com.example.storeservice.api.ProductService;
import com.example.storeservice.converter.ProductConverter;
import com.example.storeservice.domain.Product;
import com.example.storeservice.exception.ProductDuplicateCreateException;
import com.example.storeservice.exception.ReserveNotAvailableException;
import com.example.storeservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private static final String PRODUCT_DUPLICATE_CREATE_REQUEST = "Product already added, requestGuid: %s";
    private static final String PRODUCT_GUID_NOT_ENOUGH = "Product guid: %s not enough for quantity: %s";
    @Override
    @Transactional
    public void addProduct(CreateProductRequestDto dto) {
        Product product = productConverter.convert(dto);
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new ProductDuplicateCreateException(
                    String.format(PRODUCT_DUPLICATE_CREATE_REQUEST, dto.getRequestGuid()));
        }
    }

    @Override
    @Transactional
    public void reserveProduct(String productGuid, Integer quantity) {
        Optional<Product> product = productRepository.findByProductGuid(productGuid);
        if (product.isEmpty() || product.get().getAvailable(quantity) < 0) {
            throw new ReserveNotAvailableException(
                    String.format(PRODUCT_GUID_NOT_ENOUGH, productGuid, quantity)
            );
        }
        product.get().setReserved(product.get().getReserved() + quantity);
        productRepository.save(product.get());
    }
}
