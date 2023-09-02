package com.example.project.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto {
    @NotNull
    private final String category;
}
