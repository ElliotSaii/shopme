package com.shopme.shopme.dto;

import com.shopme.shopme.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String imageUrl;
    @NotNull
    private double price;
    @NotNull
    private String description;
    @NotNull
    private Long categoryId;

    public ProductDto(String name, String imageUrl, double price, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
