package com.example.project.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto  {
    private  Integer id;
    private  String category;
}
