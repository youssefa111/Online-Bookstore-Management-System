package com.example.project.dto.book;

import com.example.project.dto.category.CategoryResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class BookResponseDto implements Serializable {
    private final Integer id;
    private final String title;
    private final String description;
    private final Boolean isBorrowed;
    private final Boolean isRemoved;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final CategoryResponseDto category;
}
