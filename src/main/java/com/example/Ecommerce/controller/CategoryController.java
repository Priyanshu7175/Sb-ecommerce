package com.example.Ecommerce.controller;

import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService cs;

    public CategoryController(CategoryService cs) {
        this.cs = cs;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getCategories() {

        List<Category> allCategories = cs.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> addCategories(@Valid @RequestBody Category category){

      String status = cs.createCategory(category);
      return new ResponseEntity<>(status,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try {
            String status =  cs.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@RequestBody Category category,@PathVariable Long categoryId){

        try{

        String status = cs.updateCategory(category,categoryId);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }
        catch (ResponseStatusException e){
        return new ResponseEntity<>(e.getReason(),e.getStatusCode());
    }

    }


}
