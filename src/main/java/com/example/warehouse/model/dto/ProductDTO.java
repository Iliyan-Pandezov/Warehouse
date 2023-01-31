package com.example.warehouse.model.dto;

import com.example.warehouse.model.entity.Category;
import com.example.warehouse.model.entity.Image;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

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

    private Image image;
//    private List<Image> imageList;
}
