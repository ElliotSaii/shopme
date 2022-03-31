package com.shopme.shopme.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ApiResponse {
    private final Boolean success;
    @Getter
    private final String message;

     public Boolean isSuccess()
     {
         return success;
     }
     public String getTimestamp()
     {
         return LocalDateTime.now().toString();
     }
}
