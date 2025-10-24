package com.example.Ecommerce.controller;

import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.payload.CategoryDTO;
import com.example.Ecommerce.payload.CategoryResponse;
import com.example.Ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService cs;

    public CategoryController(CategoryService cs) {
        this.cs = cs;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getCategories() {

        CategoryResponse allCategories = cs.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> addCategories(@Valid @RequestBody CategoryDTO categoryDTO){

       CategoryDTO status = cs.createCategory(categoryDTO);
      return new ResponseEntity<>(categoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){

            CategoryDTO status =  cs.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);

    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){

        CategoryDTO status = cs.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(status,HttpStatus.OK);

    }


}
