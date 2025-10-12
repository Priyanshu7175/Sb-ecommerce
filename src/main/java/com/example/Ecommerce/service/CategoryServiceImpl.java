package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    long idx =1;
    private List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public String createCategory(Category category) {
        category.setCategoryId(idx);
        idx++;
        categories.add(category);
        return "Category added";
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found"));

        categories.remove(category);
        return "CategoryId "+categoryId+" is Deleted";
    }

    @Override
    public String updateCategory(Category category, Long categoryId) {

        Optional<Category> categoryCheck = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst();

        if(categoryCheck.isPresent()){
               Category currentCategory = categoryCheck.get();

               if(currentCategory.getCategoryName().equals(category.getCategoryName()))
               { return category.getCategoryName()+" is already the same name";}

               currentCategory.setCategoryName(category.getCategoryName());
               return category.getCategoryName()+" has been successfully updated";
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found");
        }

    }
}
