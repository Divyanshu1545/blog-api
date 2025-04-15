package com.divyanshu.bloggingapi.services.impl;

import com.divyanshu.bloggingapi.entities.Category;
import com.divyanshu.bloggingapi.exceptions.ResourceNotFoundException;
import com.divyanshu.bloggingapi.payloads.CategoryDto;
import com.divyanshu.bloggingapi.repositories.CategoryRepository;
import com.divyanshu.bloggingapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id",id));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id",id));
        categoryRepository.delete(category);

    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "id",id));

        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategpries() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }
}
