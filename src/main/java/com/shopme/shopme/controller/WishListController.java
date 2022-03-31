package com.shopme.shopme.controller;

import com.shopme.shopme.api.ApiResponse;
import com.shopme.shopme.dto.ProductDto;
import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.Product;
import com.shopme.shopme.enity.WishList;
import com.shopme.shopme.repository.WishListRepository;
import com.shopme.shopme.service.AuthenticationTokenService;
import com.shopme.shopme.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/wishlist")
public class WishListController {
    private WishListRepository wishListRepository;
    private WishListService wishListService;
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    public WishListController(WishListService wishListService,WishListRepository wishListRepository,AuthenticationTokenService authenticationTokenService)
    {
        this.wishListRepository = wishListRepository;
         this.wishListService =  wishListService;
         this.authenticationTokenService =authenticationTokenService;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token)
    {
        // authenticate the token
        authenticationTokenService.authenticate(token);
        // find the user
        AppUser user = authenticationTokenService.getUser(token);

        // save in the wish list
        WishList wishList = new WishList(user,product);
        wishListService.createWishList(wishList);
        return new ResponseEntity<>(new ApiResponse(true,"created wish list"), HttpStatus.CREATED);
    }
      // get all wishlist
    @RequestMapping(value = "/list/{token}",method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token)
    {
        // authenticate token
        authenticationTokenService.authenticate(token);
        // find the user
        AppUser user = authenticationTokenService.getUser(token);
        List<ProductDto> productDtos = wishListService.getWishListForUser(user);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

}
