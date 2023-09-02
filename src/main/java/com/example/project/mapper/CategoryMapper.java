package com.example.project.mapper;

import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryResponseDto;
import com.example.project.dto.category.CategoryUpdateDto;
import com.example.project.entity.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryResponseDto categoryToCategoryResponseDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromCategoryDto(CategoryUpdateDto categoryDto, @MappingTarget Category category);
}
