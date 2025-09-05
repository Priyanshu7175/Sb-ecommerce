package com.example.Ecommerce.controller;

import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService cs;

    public CategoryController(CategoryService cs) {
        this.cs = cs;
    }

    @GetMapping("/api/public/categories")
    public List<Category> getCategories() {
        return cs.getAllCategories();
    }

    @PostMapping("/api/public/categories")
    public String addCategories(@RequestBody Category category){

     return cs.createCategory(category);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId){
         String status = cs.deleteCategory(categoryId);
         return status;
    }


}
