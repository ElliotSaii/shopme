package com.shopme.shopme.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@NoArgsConstructor
@Getter
@Setter
public class AddToCartDto {
    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    private Integer quantity;

}
