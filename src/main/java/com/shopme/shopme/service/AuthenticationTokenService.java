package com.shopme.shopme.service;

import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.AuthenticationToken;
import com.shopme.shopme.exception.AuthenticationExecption;
import com.shopme.shopme.repository.AuthenticationTokenRepositor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@NoArgsConstructor
public class AuthenticationTokenService {
    @Autowired
    private AuthenticationTokenRepositor authenticationTokenRepositor;


    public void saveComfirmationToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepositor.save(authenticationToken);
    }


    public AuthenticationToken getToken(AppUser user) {
        return authenticationTokenRepositor.findAuthenticationTokenByAppUser(user);
    }
    public AppUser getUser(String token)
    {
        AuthenticationToken authenticationToken = authenticationTokenRepositor.findByToken(token);
        if(Objects.isNull(token)){
            return null;
        }
        // authentication is not null
        return authenticationToken.getAppUser();

    }
    public void authenticate(String token)
    {
        if(Objects.isNull(token))
        {
            throw new AuthenticationExecption("token is not exits");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationExecption("token is not valid");
        }
    }
}
