package com.example.warehouse.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Lob
//    private byte[] file;

    private String url;
    private Date creationDate;

//    @ManyToOne
//    private Product product;
}
