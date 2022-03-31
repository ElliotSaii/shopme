package com.shopme.shopme.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
public class CartDto {
   private List<CartItemDto> cartItems;
   private double totalCost;

}
