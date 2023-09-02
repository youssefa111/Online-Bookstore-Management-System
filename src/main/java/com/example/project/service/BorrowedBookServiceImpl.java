package com.example.project.service;

import com.example.auth.user.service.UserService;
import com.example.core.base.BaseResponse;
import com.example.core.exception_handling.exception.DuplicateRecordException;
import com.example.core.exception_handling.exception.InvalidDataEntryException;
import com.example.core.exception_handling.exception.RecordNotFoundException;
import com.example.core.utils.StringUtils;
import com.example.project.dto.borrowedBook.BorrowedBookRequestDto;
import com.example.project.dto.borrowedBook.BorrowedBookResponseDto;
import com.example.project.mapper.BorrowedBookMapper;
import com.example.project.repository.BorrowedBookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowedBookServiceImpl  implements BorrowedBookService{

    private final BorrowedBookRepository borrowedBookRepository;
    private final BorrowedBookMapper borrowedBookMapper;
    private final BookService bookService;
    private final UserService userService;

    @Override
    public BaseResponse<BorrowedBookResponseDto> borrowBook(BorrowedBookRequestDto borrowedBookRequestDto) {
        if(!bookService.isBookAvailable(borrowedBookRequestDto.getBookId()))
            throw new RecordNotFoundException("The Book, you Try to borrow is not Available now, please choose another one or wait till it back.");
       else if(!userService.isUserExist(borrowedBookRequestDto.getUserId()))
            throw new RecordNotFoundException("There is no id for that User,Please re-check it and try again later.");
        else if(borrowedBookRequestDto.getReturnDate().isBefore(borrowedBookRequestDto.getBorrowDate()))
            throw new InvalidDataEntryException("Invalid dates entry, the Return Date should be after borrow Date");
        else if(ChronoUnit.DAYS.between(borrowedBookRequestDto.getBorrowDate(), borrowedBookRequestDto.getReturnDate()) > 30)
            throw new InvalidDataEntryException("Sorry,You cant Borrow a book more than 30 days");
        else {
            var entity = borrowedBookMapper.borrowedBookDtoToBorrowedBook(borrowedBookRequestDto);
            entity.getBook().setIsBorrowed(Boolean.TRUE);
            var result = borrowedBookMapper.borrowedBookToBorrowedBookResponseDto(borrowedBookRepository.save(entity));
            return new BaseResponse<>(result, StringUtils.createdMsg("Borrow Book Operation"), HttpStatus.CREATED.value());
        }


    }
}
