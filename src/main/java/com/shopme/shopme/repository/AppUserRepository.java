package com.shopme.shopme.repository;

import com.shopme.shopme.enity.AppUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findUserByEmail(String email);

    AppUser findAppUserById(Long id);
}
