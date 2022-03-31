package com.shopme.shopme.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopme.shopme.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String imageUrl;

    @NotNull
    private double price;

    @NotNull
    private String description;

    // Many to one relationship
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;


    public Product(String name, String imageUrl, double price, String description, Category category) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
