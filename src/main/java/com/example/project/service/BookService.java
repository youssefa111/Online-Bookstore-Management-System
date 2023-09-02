package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.project.dto.book.BookRequestDto;
import com.example.project.dto.book.BookResponseDto;
import com.example.project.dto.book.BookUpdateDto;

import java.util.List;

public interface BookService {
    BaseResponse<BookResponseDto> save(BookRequestDto bookDto);

    BaseResponse<BookResponseDto> update(BookUpdateDto bookUpdateDto);

    BaseResponse<List<BookResponseDto>> findAll();


    BaseResponse<String> delete(Integer id);


    BaseResponse<String> remove(Integer id);

    BaseResponse<List<BookResponseDto>> findAllAvailableBooks();

    Boolean isBookAvailable(Integer id);

    void updateIsBorrowedTrueById(Integer id);

    BaseResponse<BookResponseDto> getBook(Integer id);
}
