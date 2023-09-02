package com.example.project.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateDto  {
    @NotNull
    private  Integer id;
    @Length(max = 100)
    private  String title;
    @Length(max = 500)
    private  String description;
    private  Boolean isBorrowed;
    private  Boolean isRemoved;
    private  Integer categoryId;
}
