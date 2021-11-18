package com.beglyak.newproject.service;

import com.beglyak.newproject.entity.BookEntity;
import com.beglyak.newproject.exception.BookNotFoundException;
import com.beglyak.newproject.mapper.BookToEntityMapper;
import com.beglyak.newproject.model.Book;
import com.beglyak.newproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final BookToEntityMapper mapper;

    @Override
    public Book getBookById(Long id) {
        BookEntity bookEntity = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found: id = " + id));

        return mapper.bookEntityToBook(bookEntity);

    }

    @Override
    public List<Book> getAllBooks() {
        Iterable<BookEntity> iterable = bookRepository.findAll();

        ArrayList<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : iterable) {
            books.add(mapper.bookEntityToBook(bookEntity));
        }

        return books;
    }

    @Override
    public void addBook(Book book) {
        BookEntity bookEntity = mapper.bookToBookEntity(book);

        bookRepository.save(bookEntity);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Iterable<BookEntity> iterable = bookRepository.findAllByAuthorContaining(author);
        List<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : iterable) {
            books.add(mapper.bookEntityToBook(bookEntity));
        }
        return books;
    }

//   Так выглядит код без маппера
//    @Override
//    public Book getBookById(Long id) {
//        BookEntity bookEntity = bookRepository
//                .findById(id)
//                .orElseThrow(() -> new BookNotFoundException("Book not found: id = " + id));
//
//        return new Book(bookEntity.getId(),
//                bookEntity.getAuthor(),
//                bookEntity.getTitle(),
//                bookEntity.getPrice());
//
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        Iterable<BookEntity> iterable = bookRepository.findAll();
//
//        ArrayList<Book> books = new ArrayList<>();
//        for (BookEntity bookEntity : iterable) {
//            books.add(new Book(bookEntity.getId(),
//                    bookEntity.getAuthor(),
//                    bookEntity.getTitle(),
//                    bookEntity.getPrice()));
//        }
//
//        return books;
//    }
//
//    @Override
//    public void addBook(Book book) {
//        BookEntity bookEntity = new BookEntity(null,book.getAuthor(),
//                book.getTitle(),
//                book.getPrice());
//
//        bookRepository.save(bookEntity);
//    }
}
