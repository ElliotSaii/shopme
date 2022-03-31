package com.shopme.shopme.controller;

import com.shopme.shopme.api.ApiResponse;
import com.shopme.shopme.model.Category;
import com.shopme.shopme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category)
    {
          categoryService.createCategory(category);
          return  new ResponseEntity<ApiResponse>(new ApiResponse(true,"a new category created"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Category> listCategory()
    {
        List<Category> categoryList = categoryService.findAllCategory();
        return categoryList;
    }

    @RequestMapping(value = "/update/{categoryId}", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Long id,@RequestBody Category category)
    {
        if (!categoryService.findCategoryById(id).isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "does not found with " + id + " category"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(id, category);
        return new ResponseEntity<>(new ApiResponse(true, "category updated"), HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{deleteId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("deleteId") Long id)
    {
        if (!categoryService.findCategoryById(id).isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "does not found with " + id + " category"), HttpStatus.NOT_FOUND);
        } else
        {
            categoryService.deleteCategory(id);
            return new ResponseEntity<>(new ApiResponse(true,"category deleted"),HttpStatus.OK);
        }
    }
}
