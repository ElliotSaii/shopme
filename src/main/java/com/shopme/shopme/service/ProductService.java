package com.shopme.shopme.service;

import com.shopme.shopme.dto.ProductDto;
import com.shopme.shopme.enity.Product;
import com.shopme.shopme.exception.ProductNotExistsException;
import com.shopme.shopme.model.Category;
import com.shopme.shopme.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
       List<Product> allProducts = productRepository.findAll();

       List<ProductDto> productDtos = new ArrayList<>();
       for(Product product : allProducts)
       {
           productDtos.add(getProductDto(product));
       }
       return  productDtos;
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return  productDto;
    }

    public void updateProduct(ProductDto productDto, Long id) throws Exception {
        Optional<Product> byId = productRepository.findById(id);
        if(!byId.isPresent())
        {
            throw new Exception("not present");
        }
        Product product = byId.get();
        product.setName(productDto.getName());
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent())
        {
            throw new ProductNotExistsException("Product is not valid "+ productId);
        }
        return optionalProduct.get();
    }
}
