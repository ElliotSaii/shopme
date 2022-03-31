package com.shopme.shopme.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "wishlists")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = AppUser.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne(targetEntity = Product.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    public WishList(AppUser appUser,Product product)
    {
        this.user = appUser;
        this.product = product;
        this.createdTime =LocalDateTime.now();
    }
}
