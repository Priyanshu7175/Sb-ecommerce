package com.example.Ecommerce.controller;

import com.example.Ecommerce.config.AppConstants;
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
    public ResponseEntity<CategoryResponse> getCategories(@RequestParam(name="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                          @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
                                                          @RequestParam(name="sortBy",defaultValue = AppConstants.SORT_BY_CATEGORYID,required = false) String sortBy,
                                                          @RequestParam(name="sortOrder",defaultValue = AppConstants.SORT_ORDER,required = false) String sortOrder
                                                          ) {

        CategoryResponse allCategories = cs.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> addCategories(@Valid @RequestBody CategoryDTO categoryDTO){

       CategoryDTO status = cs.createCategory(categoryDTO);
      return new ResponseEntity<>(status,HttpStatus.CREATED);
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
