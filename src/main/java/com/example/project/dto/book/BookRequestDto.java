package com.example.project.dto.book;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookRequestDto implements Serializable {
    @NotNull
    private final String title;
    @NotNull
    private final String description;
    @NotNull
    private final Integer categoryId;
}
