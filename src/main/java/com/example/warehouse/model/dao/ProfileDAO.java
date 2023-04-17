package com.example.warehouse.model.dao;

import com.example.warehouse.model.entity.User;


public record ProfileDAO (
    Long id,

    String firstName,

    String lastName,

    String phoneNumber,

    User user

    ){

}
