package com.shopme.shopme.repository;

import com.shopme.shopme.enity.Product;
import com.shopme.shopme.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Category> findById(Category categoryId);


}
