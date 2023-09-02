package com.example.project.controller;

import com.example.core.base.BaseResponse;
import com.example.core.constant.AppConstants;
import com.example.project.dto.borrowedBook.BorrowedBookRequestDto;
import com.example.project.dto.borrowedBook.BorrowedBookResponseDto;
import com.example.project.service.BorrowedBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(AppConstants.USER)
    public BaseResponse<BorrowedBookResponseDto> borrowBook(@Valid @RequestBody BorrowedBookRequestDto borrowedBookRequestDto){
        return borrowedBookService.borrowBook(borrowedBookRequestDto);
    }

}
