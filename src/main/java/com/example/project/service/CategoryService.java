package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryResponseDto;
import com.example.project.dto.category.CategoryUpdateDto;
import com.example.project.entity.Category;

import java.util.Optional;
import java.util.Set;

public interface CategoryService {
    BaseResponse<CategoryResponseDto> save(CategoryDto categoryDto);

    BaseResponse<CategoryResponseDto> update(CategoryUpdateDto categoryDto);

     Boolean existsById(Integer id);
    Boolean existsByCategory(String category);

    BaseResponse<Set<CategoryResponseDto>> findAll();

    BaseResponse<String> delete(Integer id);
}
