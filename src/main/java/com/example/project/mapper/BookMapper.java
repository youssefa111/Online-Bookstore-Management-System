package com.example.project.mapper;

import com.example.project.dto.book.BookRequestDto;
import com.example.project.dto.book.BookResponseDto;
import com.example.project.dto.book.BookUpdateDto;
import com.example.project.entity.Book;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
uses = {
        CategoryMapper.class
})
public interface BookMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Book bookRequestDtoToBook(BookRequestDto bookRequestDto);

    BookResponseDto bookToBookResponseDto(Book book);

    @Mapping(source = "categoryId", target = "category.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book updateBookFromBookUpdateDto(BookUpdateDto bookRequestDto, @MappingTarget Book book);

}
