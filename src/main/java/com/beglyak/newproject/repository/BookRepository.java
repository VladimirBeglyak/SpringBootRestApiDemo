package com.beglyak.newproject.repository;

import com.beglyak.newproject.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity,Long> {
    List<BookEntity> findAllByAuthorContaining(String author); //SELECT * FROM books WHERE author LIKE '%Bloch%'
}
