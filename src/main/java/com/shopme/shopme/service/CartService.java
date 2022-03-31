package com.shopme.shopme.service;

import com.shopme.shopme.dto.cart.AddToCartDto;
import com.shopme.shopme.dto.cart.CartDto;
import com.shopme.shopme.dto.cart.CartItemDto;
import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.Cart;
import com.shopme.shopme.enity.Product;
import com.shopme.shopme.exception.AuthenticationExecption;
import com.shopme.shopme.exception.CustomError;
import com.shopme.shopme.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private ProductService productService;

    public void addToCart(AddToCartDto addToCartDto, AppUser user) {
        //validate if the product is valid
        Product product  = productService.findById(addToCartDto.getProductId());

        // save to cart
        Cart cart = new Cart();
        cart.setAppUser(user);
        cart.setProduct(product);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedTime(new Date());
        cartRepository.save(cart);
    }

    public CartDto getAllCartItem(AppUser user) {
        List<Cart> cartList = cartRepository.findAllByAppUserOrderByCreatedTimeDesc(user);
        List<CartItemDto> cartItem = new ArrayList<>();
        double totalCost = 0;
        for(Cart cart : cartList)
        {
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItem.add(cartItemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItem);


        return cartDto;

    }

    public void deleteCartItem(Long id,AppUser user) {
        // validate the user id
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if(!optionalCart.isPresent())
        {
            throw new CustomError("Cart item is not valid");
        }
        Cart cart = optionalCart.get();
        if(cart.getAppUser() != user)
        {
            throw new CustomError("User is not exits");
        }
        cartRepository.delete(cart);
    }
}
