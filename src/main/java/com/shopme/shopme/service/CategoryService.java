package com.shopme.shopme.service;

import com.shopme.shopme.model.Category;
import com.shopme.shopme.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category)
    {
        categoryRepository.save(category);
    }

    public List<Category> findAllCategory() {
       List<Category> categoryList = categoryRepository.findAll();
       return  categoryList;
    }
    public Optional<Category> findCategoryById(Long id)
    {
        Optional<Category> categoryIdexites = categoryRepository.findById(id);

        return categoryIdexites;
    }

    public Category editCategory(Long id, Category category) {
        Category editcategory = categoryRepository.getById(id);
            editcategory.setCategoryName(category.getCategoryName());
            editcategory.setDescription(category.getDescription());
            editcategory.setImageUrl(category.getImageUrl());
            categoryRepository.save(editcategory);
         return editcategory;
    }

    public void deleteCategory(Long id) {
       Category deleteCategory = categoryRepository.getById(id);
       deleteCategory.setCategoryName(null);
       deleteCategory.setDescription(null);
        categoryRepository.deleteById(id);
    }
}
