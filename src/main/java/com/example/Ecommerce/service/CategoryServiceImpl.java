package com.example.Ecommerce.service;

import com.example.Ecommerce.exception.APIException;
import com.example.Ecommerce.exception.ResourceNotFoundException;
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

        List<Category> allCategories = categoryRepo.findAll();
        if(allCategories.isEmpty()){
            throw new APIException("No category created");
        }
        return allCategories;
    }

    @Override
    public String createCategory(Category category) {
        Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if(savedCategory != null){
            throw new APIException("Category with name "+category.getCategoryName()+" already exists");
        }
        categoryRepo.save(category);
        return "Category added";
    }

    @Override
    public String deleteCategory(Long categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);
        return "CategoryId "+categoryId+" is Deleted";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {

        Category categoryCheck = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        categoryRepo.save(category);
        return category.getCategoryName()+" has been updated";
    }
}
