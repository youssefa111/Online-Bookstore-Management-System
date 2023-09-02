package com.example.project.dto.borrowedBook;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookRequestDto {
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private  LocalDate borrowDate;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private  LocalDate returnDate;
    @NotNull
    private  Long userId;
    @NotNull
    private  Integer bookId;
}
