package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.core.exception_handling.exception.RecordNotFoundException;
import com.example.core.utils.StringUtils;
import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryResponseDto;
import com.example.project.dto.category.CategoryUpdateDto;
import com.example.project.entity.Category;
import com.example.project.mapper.CategoryMapper;
import com.example.project.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl  implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public BaseResponse<CategoryResponseDto> save(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        var result = categoryMapper.categoryToCategoryResponseDto(categoryRepository.save(category));
        return new BaseResponse<>(result, StringUtils.createdMsg("Category"), HttpStatus.CREATED.value()) ;
    }

    @Transactional
    @Override
    public BaseResponse<CategoryResponseDto> update(CategoryUpdateDto categoryUpdateDto) {
        var oldEntity =  categoryRepository.findById(categoryUpdateDto.id()).orElseThrow( ()-> new RecordNotFoundException("this Category with id:-{"+ categoryUpdateDto.id() + "} not found"));
        categoryMapper.updateCategoryFromCategoryDto(categoryUpdateDto,oldEntity);
       var newEntity = categoryRepository.save(oldEntity);
        var result = categoryMapper.categoryToCategoryResponseDto(newEntity);
        return new BaseResponse<>(result, StringUtils.updateMsg("Category"), HttpStatus.OK.value()) ;
    }

    @Override
    public Boolean existsById(int id) {
        return  categoryRepository.existsById(id);

    }

    @Override
    public BaseResponse<Set<CategoryResponseDto>> findAll() {
        var entities = categoryRepository.findAll();
        Set<CategoryResponseDto> result = new HashSet<>();
        entities.forEach(category -> result.add(categoryMapper.categoryToCategoryResponseDto(category)));
        return new BaseResponse<>(result);
    }

    @Transactional
    @Override
    public BaseResponse<String> delete(Integer id) {
        categoryRepository.deleteById(id);
        return new BaseResponse<>(null, StringUtils.deleteMsg("Category")) ;
    }
}
