package com.shopme.shopme.controller;

import com.shopme.shopme.api.ApiResponse;
import com.shopme.shopme.dto.cart.AddToCartDto;
import com.shopme.shopme.dto.cart.CartDto;
import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.service.AppUserService;
import com.shopme.shopme.service.AuthenticationTokenService;
import com.shopme.shopme.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;
    private AuthenticationTokenService authenticationTokenService;
    private AppUserService appUserService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token) {
        // authenticate the token
        authenticationTokenService.authenticate(token);

        // authenticate the user
        AppUser user = authenticationTokenService.getUser(token);
        cartService.addToCart(addToCartDto, user);
        return new ResponseEntity<>(new ApiResponse(true, "success"), HttpStatus.CREATED);
    }

    // get all cart item
    @GetMapping("/list")
    public ResponseEntity<CartDto> getAllCartItem(@RequestParam("token") String token) {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        AppUser user = authenticationTokenService.getUser(token);

        CartDto cartDto = cartService.getAllCartItem(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // delete cart item
    @RequestMapping(value = "/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long id,@RequestParam("token")String token) {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        AppUser user = authenticationTokenService.getUser(token);

        cartService.deleteCartItem(id,user);
        return new ResponseEntity<>(new ApiResponse(true, "success"), HttpStatus.OK);
    }
}
