package com.shopme.shopme.dto.cart;

import com.shopme.shopme.enity.Cart;
import com.shopme.shopme.enity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private Product product;

    public CartItemDto(Cart cart)
    {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());

    }
}
