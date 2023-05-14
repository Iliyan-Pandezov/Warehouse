package com.example.warehouse.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    private String addressName;

    private String town;

    private String neighbourhood;

    private String streetName;

    private String streetNumber;

    private String buildingNumber;

    private String entrance;

    private String floor;

    private String apartment;
}
