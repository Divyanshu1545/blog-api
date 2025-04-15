package com.divyanshu.bloggingapi.services;

import com.divyanshu.bloggingapi.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);

    void deleteCategory(Integer id);

    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategpries();

}
