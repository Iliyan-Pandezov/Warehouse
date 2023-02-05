package com.example.warehouse.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
//    @Type(type = "uuid-char")
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String details;

    private BigDecimal price;

//    @OneToMany
//    private List<Image> imageList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> imageList;

    @CreationTimestamp
    private Date addedOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

}
