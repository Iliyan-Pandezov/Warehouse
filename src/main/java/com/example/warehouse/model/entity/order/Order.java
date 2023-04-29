package com.example.warehouse.model.entity.order;

import com.example.warehouse.model.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;


    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShippingAddress address;

    @ManyToOne
    private User user;

    private boolean isCompleted = false;

    @CreationTimestamp
    private Date submittedOn;

    private LocalDateTime completedOn;

    private BigDecimal total;

    private boolean isCanceled = false;
}
