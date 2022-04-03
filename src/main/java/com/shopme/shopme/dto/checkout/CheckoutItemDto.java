package com.shopme.shopme.dto.checkout;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CheckoutItemDto {
    private String productName;
    private int quantity;
    private double price;
    private long productId;
    private int userId;
}
