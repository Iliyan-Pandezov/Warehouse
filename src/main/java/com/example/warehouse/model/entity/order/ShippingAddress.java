package com.example.warehouse.model.entity.order;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "shippingAddresses")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private String town;

    private String neighbourhood;

    private String streetName;

    private String streetNumber;

    private String buildingNumber;

    private String entrance;

    private String floor;

    private String apartment;
}
