package com.shopme.shopme.controller;

import com.shopme.shopme.dto.ResponseDto;
import com.shopme.shopme.dto.user.LoginDto;
import com.shopme.shopme.dto.user.LoginResponseDto;
import com.shopme.shopme.dto.user.RegisterationDto;
import com.shopme.shopme.repository.AppUserRepository;
import com.shopme.shopme.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/appuser")
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppUserService appUserService;

    // sing up
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseDto registerNewAccount(@RequestBody RegisterationDto registeration)
    {
    return  appUserService.SignUp(registeration);
    }
    // login

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponseDto loginAccount(@RequestBody LoginDto loginDto)
    {
        return appUserService.Login(loginDto);
    }
}
