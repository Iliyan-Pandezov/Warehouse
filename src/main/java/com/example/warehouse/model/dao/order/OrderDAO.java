package com.example.warehouse.model.dao.order;

import com.example.warehouse.model.entity.Address;
import com.example.warehouse.model.entity.User;

import java.math.BigDecimal;
import java.util.Date;

public record OrderDAO (
        Long id,
        Address address,
        User user,
        boolean isCompleted,
        Date submittedOn,
        BigDecimal total,
        boolean isCanceled
){
}
