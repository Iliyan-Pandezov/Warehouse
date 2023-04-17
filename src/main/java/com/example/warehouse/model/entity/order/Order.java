package com.example.warehouse.model.entity.order;

import com.example.warehouse.model.entity.Address;
import com.example.warehouse.model.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Address address;

    @ManyToOne
    private User user;

    private boolean isCompleted = false;

    @CreationTimestamp
    private Date submittedOn;

    private BigDecimal total;

    private boolean isCanceled = false;
}
