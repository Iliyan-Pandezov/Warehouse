package com.example.warehouse.model.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductDTO {
    @NotNull
    private String name;
    @NotNull
    private String details;
    @NotNull
    private BigDecimal price;

    private String image;

    public ProductDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
