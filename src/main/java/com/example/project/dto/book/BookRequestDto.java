package com.example.project.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto  {
    @NotBlank
    @Length(max = 100)
    private String title;
    @NotBlank
    @Length(max = 500)
    private  String description;
    @NotNull
    private  Integer categoryId;
}
