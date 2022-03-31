package com.shopme.shopme.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;


    @NotNull
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "quantity")
    private int quantity;
}

