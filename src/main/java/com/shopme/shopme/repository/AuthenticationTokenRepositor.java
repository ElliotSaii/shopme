package com.shopme.shopme.repository;

import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepositor extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findAuthenticationTokenByAppUser(AppUser user);
    AuthenticationToken findByToken(String token);


}
