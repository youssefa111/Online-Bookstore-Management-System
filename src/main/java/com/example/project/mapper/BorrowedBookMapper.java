package com.example.project.mapper;

import com.example.auth.user.mapper.UserMapper;
import com.example.project.dto.borrowedBook.BorrowedBookRequestDto;
import com.example.project.dto.borrowedBook.BorrowedBookResponseDto;
import com.example.project.entity.BorrowedBook;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                BookMapper.class,
                UserMapper.class
        })
public interface BorrowedBookMapper {
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "bookId", target = "book.id")
    BorrowedBook borrowedBookDtoToBorrowedBook(BorrowedBookRequestDto borrowedBookDto);

    @InheritInverseConfiguration(name = "borrowedBookResponseDtoToBorrowedBook")
    BorrowedBookResponseDto borrowedBookToBorrowedBookResponseDto(BorrowedBook borrowedBook);

    @InheritConfiguration(name = "borrowedBookDtoToBorrowedBook")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BorrowedBook updateBorrowedBookFromBorrowedBookDto(BorrowedBookRequestDto borrowedBookDto, @MappingTarget BorrowedBook borrowedBook);


}
