package com.beglyak.newproject.mapper;

import com.beglyak.newproject.entity.BookEntity;
import com.beglyak.newproject.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") //чтобы спринг мог создавать bean класса
//@Mapping(target = "", source = "") - если поля имеют отличающиеся поля
public interface BookToEntityMapper {
    BookEntity bookToBookEntity(Book book);
    Book bookEntityToBook(BookEntity bookEntity);
}
