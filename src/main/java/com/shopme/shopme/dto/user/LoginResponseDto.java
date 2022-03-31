package com.shopme.shopme.dto.user;

import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.AuthenticationToken;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
    private  String status;
    private  String token;

    public LoginResponseDto(String status, String token)
    {
        this.status =status;
        this.token =token;
    }
}
