package com.example.project.service;

import com.example.core.base.BaseResponse;
import com.example.core.exception_handling.exception.DuplicateRecordException;
import com.example.core.utils.StringUtils;
import com.example.project.dto.borrowedBook.BorrowedBookRequestDto;
import com.example.project.dto.borrowedBook.BorrowedBookResponseDto;
import com.example.project.mapper.BorrowedBookMapper;
import com.example.project.repository.BorrowedBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowedBookServiceImpl  implements BorrowedBookService{

    private final BorrowedBookRepository borrowedBookRepository;
    private final BorrowedBookMapper borrowedBookMapper;
    private final BookService bookService;
    @Override
    public BaseResponse<BorrowedBookResponseDto> borrowBook(BorrowedBookRequestDto borrowedBookRequestDto) {
        if(!bookService.isBookAvailable(borrowedBookRequestDto.getBookId()))
            throw new DuplicateRecordException("The Book, you Try to borrow is not Available now, please choose another one or wait till it back.");

        var entity = borrowedBookMapper.borrowedBookDtoToBorrowedBook(borrowedBookRequestDto);
        entity.getBook().setIsBorrowed(true);
        var result = borrowedBookMapper.borrowedBookToBorrowedBookResponseDto(borrowedBookRepository.save(entity));
        return new BaseResponse<>(result, StringUtils.createdMsg("Borrow Book Operation"), HttpStatus.CREATED.value());
    }
}
