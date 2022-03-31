package com.shopme.shopme.service;

import com.shopme.shopme.api.ApiResponse;
import com.shopme.shopme.dto.ProductDto;
import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.AuthenticationToken;
import com.shopme.shopme.enity.Product;
import com.shopme.shopme.enity.WishList;
import com.shopme.shopme.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    private WishListRepository wishListRepository;
    private AuthenticationTokenService authenticationTokenService;
    private ProductService productService;

    @Autowired
    public WishListService(WishListRepository wishListRepository, AuthenticationTokenService authenticationTokenService,ProductService productService) {
        this.productService = productService;
        this.authenticationTokenService = authenticationTokenService;
        this.wishListRepository = wishListRepository;
    }


    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

 public List<ProductDto> getWishListForUser(AppUser user) {

        List<WishList> wishlists = wishListRepository.findAllByUserOrderByCreatedTimeDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList list : wishlists) {
            productDtos.add(productService.getProductDto(list.getProduct()));
        }
        return productDtos ;
    }


}
