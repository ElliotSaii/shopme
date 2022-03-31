package com.shopme.shopme.repository;

import com.shopme.shopme.dto.ProductDto;
import com.shopme.shopme.enity.AppUser;
import com.shopme.shopme.enity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long> {
    List<WishList> findAllByUserOrderByCreatedTimeDesc(AppUser user);
}
