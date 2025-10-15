package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    //long idx =1;
    //private List<Category> categories = new ArrayList<>();
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public String createCategory(Category category) {

        categoryRepo.save(category);
        return "Category added";
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        categoryRepo.delete(category);
        return "CategoryId "+categoryId+" is Deleted";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {

        Category categoryCheck = categoryRepo.findById(categoryId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found"));
        category.setCategoryId(categoryId);
        categoryRepo.save(category);
        return category.getCategoryName()+" has been updated";
    }
}
