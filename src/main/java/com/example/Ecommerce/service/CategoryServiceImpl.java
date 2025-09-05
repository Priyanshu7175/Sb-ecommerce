package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Category category = categories.stream().filter(c -> c.getCategoryId().equals(categoryId)).findFirst().orElse(null);
        if(category == null){
            return "Category not found";
        }
        categories.remove(category);
        return "CategoryId "+categoryId+" is Deleted";
    }
}
