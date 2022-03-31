package com.shopme.shopme.controller;

import com.shopme.shopme.api.ApiResponse;
import com.shopme.shopme.dto.ProductDto;
import com.shopme.shopme.enity.Product;
import com.shopme.shopme.model.Category;
import com.shopme.shopme.repository.CategoryRepository;
import com.shopme.shopme.repository.ProductRepository;
import com.shopme.shopme.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@RequestMapping(value = "/product")

public class ProductController {
    private ProductRepository productRepository;
    private ProductService productService;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/add/{categoryId}", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> addProduct(@PathVariable("categoryId") Long id, @RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exit"), HttpStatus.NOT_FOUND);
        }
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "add new product"), HttpStatus.CREATED);
    }

    /*
    list all products from databases;
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getListOfProducts() {

        List<ProductDto> productDtos = productService.getAllProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long id, @RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "product does not exit"), HttpStatus.NOT_FOUND);
        }
        try {

            productService.updateProduct(productDto, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ApiResponse(true, "update product"), HttpStatus.CREATED);
    }
}
