package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductDTO {
    @NotNull
    private String name;
    @NotNull
    private String details;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Category category;
}
