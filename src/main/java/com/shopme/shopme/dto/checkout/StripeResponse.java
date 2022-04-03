package com.shopme.shopme.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StripeResponse {
    private String sessionId;
}
