package com.example.project.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryResponseDto implements Serializable {
    @NotNull
    private final Integer id;
    @NotNull
    private final String category;
}
