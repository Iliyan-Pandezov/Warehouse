package com.example.warehouse.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Profile profile;

    private String name;

    private String town;

    private String neighbourhood;

    private String streetName;

    private String streetNumber;

    private String buildingNumber;

    private String entrance;

    private String floor;

    private String apartment;


}
