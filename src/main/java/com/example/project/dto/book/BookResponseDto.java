package com.example.project.dto.book;

import com.example.project.dto.category.CategoryResponseDto;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto  {
    private  Integer id;
    private  String title;
    private  String description;
    private  Boolean isBorrowed;
    private  Boolean isRemoved;
    private  Instant createdAt;
    private  Instant updatedAt;
    private  CategoryResponseDto category;
}
