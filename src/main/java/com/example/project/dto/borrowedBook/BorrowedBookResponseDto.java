package com.example.project.dto.borrowedBook;

import com.example.project.dto.book.BookResponseDto;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BorrowedBookResponseDto implements Serializable {
    private final Integer borrowId;
    private final LocalDate borrowDate;
    private final LocalDate returnDate;
    private final Long userId;
    private final BookResponseDto book;
}
