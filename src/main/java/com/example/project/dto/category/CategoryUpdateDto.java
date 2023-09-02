package com.example.project.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.*;


public record CategoryUpdateDto(@NotNull Integer id, @NotNull String category) {

}
