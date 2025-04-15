package com.divyanshu.bloggingapi.controllers;

import com.divyanshu.bloggingapi.entities.Category;
import com.divyanshu.bloggingapi.payloads.ApiResponse;
import com.divyanshu.bloggingapi.payloads.CategoryDto;
import com.divyanshu.bloggingapi.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Integer id,@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id){
        categoryService.getCategoryById(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully.",true),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categories = categoryService.getAllCategpries();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
