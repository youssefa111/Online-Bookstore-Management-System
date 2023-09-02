package com.example.project.dto.borrowedBook;

import com.example.project.dto.book.BookResponseDto;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedBookResponseDto {
    private  Integer borrowId;
    private  LocalDate borrowDate;
    private  LocalDate returnDate;
    private  Long userId;
    private  BookResponseDto book;
}
