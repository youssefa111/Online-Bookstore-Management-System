package com.example.project.controller;

import com.example.core.base.BaseResponse;
import com.example.core.constant.AppConstants;
import com.example.project.dto.book.BookRequestDto;
import com.example.project.dto.book.BookResponseDto;
import com.example.project.dto.book.BookUpdateDto;
import com.example.project.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${baseUrl}/books")
@RequiredArgsConstructor
@Validated
@Secured(AppConstants.ADMIN)
public class BookController {

    final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<BookResponseDto> createBook(@Valid  @RequestBody BookRequestDto BookDto){
        return bookService.save(BookDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<BookResponseDto> updateBook(@Valid  @RequestBody BookUpdateDto BookUpdateDto){
        return bookService.update(BookUpdateDto);
    }

    @GetMapping("/{getById}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<BookResponseDto> getBook(@Valid @NotNull @PathVariable("getById")Integer id){
        return bookService.getBook(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<BookResponseDto>> findAll(){
        return bookService.findAll();
    }

    @GetMapping("/allAvailable")
    @ResponseStatus(HttpStatus.OK)
    @Secured(AppConstants.USER)
    public BaseResponse<List<BookResponseDto>> findAllAvailableBooks(){
        return bookService.findAllAvailableBooks();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> delete( @Valid @NotNull @PathVariable("id")Integer id){
        return bookService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<String> remove( @Valid @NotNull @PathVariable("id")Integer id){
        return bookService.remove(id);
    }

}
