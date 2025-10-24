package com.example.Ecommerce.service;

import com.example.Ecommerce.exception.APIException;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.model.Category;
import com.example.Ecommerce.payload.CategoryDTO;
import com.example.Ecommerce.payload.CategoryResponse;
import com.example.Ecommerce.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize) {

        Pageable pageDetails =  PageRequest.of(pageNumber,pageSize);
        Page<Category> categoryPage = categoryRepo.findAll(pageDetails);

        List<Category> allCategories = categoryPage.getContent();
        if(allCategories.isEmpty()){
            throw new APIException("No category created");
        }
        List<CategoryDTO> categoryDTOList = allCategories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOList);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category =modelMapper.map(categoryDTO,Category.class);
        Category dbCategory = categoryRepo.findByCategoryName(category.getCategoryName());
        if(dbCategory != null){
            throw new APIException("Category with name "+category.getCategoryName()+" already exists");
        }
        Category savedCategory= categoryRepo.save(category);
        CategoryDTO reMapDTO =modelMapper.map(savedCategory,CategoryDTO.class);
        return reMapDTO;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);

        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category = modelMapper.map(categoryDTO,Category.class);
        Category categoryCheck = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryId(categoryId);
        categoryRepo.save(category);
        categoryDTO = modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }
}
