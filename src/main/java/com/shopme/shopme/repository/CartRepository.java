package com.shopme.shopme.repository;

import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByAppUserOrderByCreatedTimeDesc(AppUser user);


}
