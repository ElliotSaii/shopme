package com.shopme.shopme.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //private String confirmPassword;
}
