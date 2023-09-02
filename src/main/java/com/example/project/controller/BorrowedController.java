package com.example.project.controller;

import com.example.core.base.BaseResponse;
import com.example.project.dto.borrowedBook.BorrowedBookRequestDto;
import com.example.project.dto.borrowedBook.BorrowedBookResponseDto;
import com.example.project.service.BorrowedBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("${baseUrl}/borrow")
public class BorrowedController {

    private final BorrowedBookService borrowedBookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<BorrowedBookResponseDto> borrowBook(@Valid @RequestBody BorrowedBookRequestDto borrowedBookRequestDto){
        return borrowedBookService.borrowBook(borrowedBookRequestDto);
    }

}
