package com.example.project.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookUpdateDto implements Serializable {
    @NotNull
    private final Integer id;
    private final String title;
    private final String description;
    private final Boolean isBorrowed;
    private final Boolean isRemoved;
    private final Integer categoryId;
}
