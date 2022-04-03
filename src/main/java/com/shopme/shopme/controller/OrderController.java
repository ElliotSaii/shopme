package com.shopme.shopme.controller;

import com.shopme.shopme.dto.checkout.CheckoutItemDto;
import com.shopme.shopme.dto.checkout.StripeResponse;
import com.shopme.shopme.service.AuthenticationTokenService;
import com.shopme.shopme.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
@AllArgsConstructor
public class OrderController {
    private AuthenticationTokenService authenticationTokenService;
    private OrderService orderService;

    // stripe session checkout api
    @RequestMapping(value = "/create-checkout-session", method = RequestMethod.POST)
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDto) throws StripeException {
        Session session = orderService.createSession(checkoutItemDto);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }



}
