package com.beglyak.newproject.mapper;

import com.beglyak.newproject.dto.BookRequest;
import com.beglyak.newproject.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookToDtoMapper {
    Book addBookRequestToBook(BookRequest bookRequest);
}
