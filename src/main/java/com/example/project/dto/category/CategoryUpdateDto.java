package com.example.project.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {
    @NotNull
    private Integer id;
    @NotBlank
    private String category;
}
