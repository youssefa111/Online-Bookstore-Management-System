package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.core.exception_handling.exception.RecordNotFoundException;
import com.example.core.utils.StringUtils;
import com.example.project.dto.book.BookRequestDto;
import com.example.project.dto.book.BookResponseDto;
import com.example.project.dto.book.BookUpdateDto;
import com.example.project.dto.category.CategoryResponseDto;
import com.example.project.entity.Book;
import com.example.project.mapper.BookMapper;
import com.example.project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public BaseResponse<BookResponseDto> save(BookRequestDto bookDto) {
        if(!categoryService.existsById(bookDto.getCategoryId())) throw new RecordNotFoundException("this Category with id:-{" + bookDto.getCategoryId() + "} not found");
        Book book = bookMapper.bookRequestDtoToBook(bookDto);
        var result = bookMapper.bookToBookResponseDto(bookRepository.save(book));
        return new BaseResponse<>(result, StringUtils.createdMsg("Book"), HttpStatus.CREATED.value());
    }

    @Transactional
    @Override
    public BaseResponse<BookResponseDto> update(BookUpdateDto bookUpdateDto) {
        var oldEntity =  bookRepository.findById(bookUpdateDto.getId()).orElseThrow( ()-> new RecordNotFoundException("this Book with id:-{"+ bookUpdateDto.getId() + "} not found"));
        bookMapper.updateBookFromBookUpdateDto(bookUpdateDto,oldEntity);
        var newEntity = bookRepository.save(oldEntity);
        var result = bookMapper.bookToBookResponseDto(newEntity);
        return new BaseResponse<>(result, StringUtils.updateMsg("Book"), HttpStatus.OK.value()) ;
    }


    @Override
    public BaseResponse<List<BookResponseDto>> findAll() {
        var entities = bookRepository.findAll();
        List<BookResponseDto> result = new ArrayList<>();
        entities.forEach(book -> result.add(bookMapper.bookToBookResponseDto(book)));
        return new BaseResponse<>(result);
    }

    @Transactional
    @Override
    public BaseResponse<String> delete(Integer id) {
        bookRepository.deleteById(id);
        return new BaseResponse<>(null, StringUtils.deleteMsg("Book")) ;
    }

    @Transactional
    @Override
    public BaseResponse<String> remove(Integer id) {
        bookRepository.updateIsRemovedById(Boolean.TRUE,id);
        return new BaseResponse<>(null, StringUtils.removeMsg("Book")) ;
    }

    @Override
    public BaseResponse<List<BookResponseDto>> findAllAvailableBooks() {
        var entities = bookRepository.findByIsBorrowedFalseAndIsRemovedFalse();
        List<BookResponseDto> result = new ArrayList<>();
        entities.forEach(book -> result.add(bookMapper.bookToBookResponseDto(book)));
        return new BaseResponse<>(result);
    }

    @Override
    public Boolean isBookAvailable(Integer id) {
        return bookRepository.existsByIsBorrowedFalseAndIsRemovedFalseAndId(id);
    }

    @Transactional
    @Override
    public void updateIsBorrowedTrueById(Integer id) {
        bookRepository.updateIsBorrowedById(Boolean.TRUE,id);
    }

    @Override
    public BaseResponse<BookResponseDto> getBook(Integer id) {
        BookResponseDto result = bookMapper.bookToBookResponseDto(bookRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("this Book with id:-{"+ id + "} not found")));
        return new BaseResponse<>(result);
    }
}
