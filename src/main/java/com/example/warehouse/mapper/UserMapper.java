package com.example.warehouse.mapper;

import com.example.warehouse.model.dto.UserRegistrationDTO;
import com.example.warehouse.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "active", constant = "true")
    User DTOToUser(UserRegistrationDTO userRegistrationDTO);

    UserRegistrationDTO UserToDTO (User user);
}
