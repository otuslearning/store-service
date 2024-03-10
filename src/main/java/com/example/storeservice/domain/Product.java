package com.example.storeservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productGuid;
    private Integer quantity;
    private Integer reserved = 0;
    @Column(nullable = false, unique = true)
    private String requestGuid;

    public Integer getAvailable(Integer quantity) {
        return this.quantity - this.reserved - quantity;
    }
}
