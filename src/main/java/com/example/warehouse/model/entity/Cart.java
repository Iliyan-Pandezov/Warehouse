package com.example.warehouse.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    private int quantity;

    @Transient
    public BigDecimal getSubtotal(){
        return this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
