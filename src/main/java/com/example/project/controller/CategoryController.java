package com.example.project.controller;

import com.example.core.base.BaseResponse;
import com.example.core.constant.AppConstants;
import com.example.project.dto.category.CategoryDto;
import com.example.project.dto.category.CategoryResponseDto;
import com.example.project.dto.category.CategoryUpdateDto;
import com.example.project.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("{baseUrl}/categories")
@RequiredArgsConstructor
@Validated
@Secured(AppConstants.ADMIN)
public class CategoryController {
    final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CategoryResponseDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
        return categoryService.save(categoryDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryResponseDto> updateCategory(@Valid  @RequestBody CategoryUpdateDto categoryUpdateDto){
        return categoryService.update(categoryUpdateDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Secured({
            AppConstants.ADMIN,
            AppConstants.USER
    })
    public BaseResponse<Set<CategoryResponseDto>> findAll(){
        return categoryService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> delete( @Valid @NotNull @PathVariable("id")Integer id){
        return categoryService.delete(id);
    }

}
