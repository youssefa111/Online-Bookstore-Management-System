package com.example.project.dto.borrowedBook;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
public class BorrowedBookRequestDto implements Serializable {
    @NotNull
    private final LocalDate borrowDate;
    @NotNull
    private final LocalDate returnDate;
    @NotNull
    private final Long userId;
    @NotNull
    private final Integer bookId;
}
