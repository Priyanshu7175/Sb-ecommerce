package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();
    public String createCategory(Category category);

    String deleteCategory(Long categoryId);

    String updateCategory(Category category, Long categoryId);
}
