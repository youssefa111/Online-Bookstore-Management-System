package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.core.exception_handling.exception.DuplicateRecordException;
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
        if(existsByCategory(categoryDto.getCategory())) throw new DuplicateRecordException("this Category with category name:-{" + categoryDto.getCategory() + "} is already exist");
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        var result = categoryMapper.categoryToCategoryResponseDto(categoryRepository.save(category));
        return new BaseResponse<>(result, StringUtils.createdMsg("Category"), HttpStatus.CREATED.value()) ;
    }

    @Transactional
    @Override
    public BaseResponse<CategoryResponseDto> update(CategoryUpdateDto categoryUpdateDto) {
        var oldEntity =  categoryRepository.findById(categoryUpdateDto.getId()).orElseThrow( ()-> new RecordNotFoundException("this Category with id:-{"+ categoryUpdateDto.getId() + "} not found"));
        categoryMapper.updateCategoryFromCategoryDto(categoryUpdateDto,oldEntity);
       var newEntity = categoryRepository.save(oldEntity);
        var result = categoryMapper.categoryToCategoryResponseDto(newEntity);
        return new BaseResponse<>(result, StringUtils.updateMsg("Category"), HttpStatus.OK.value()) ;
    }

    @Override
    public Boolean existsByCategory(String category) {
        return  categoryRepository.existsByCategory(category);

    }

    @Override
    public Boolean existsById(Integer id) {
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

        if (!categoryRepository.existsById(id)) {
            throw new RecordNotFoundException("Category with ID " + id + " not found");
        }

        categoryRepository.deleteById(id);
        return new BaseResponse<>(null, StringUtils.deleteMsg("Category")) ;
    }
}
