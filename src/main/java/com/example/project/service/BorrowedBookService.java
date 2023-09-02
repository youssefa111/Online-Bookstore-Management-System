package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.project.dto.borrowedBook.BorrowedBookRequestDto;
import com.example.project.dto.borrowedBook.BorrowedBookResponseDto;

public interface BorrowedBookService {
    BaseResponse<BorrowedBookResponseDto> borrowBook(BorrowedBookRequestDto borrowedBookRequestDto);
}
