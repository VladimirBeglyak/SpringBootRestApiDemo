package com.beglyak.newproject.controller;

import com.beglyak.newproject.dto.BookRequest;
import com.beglyak.newproject.mapper.BookToDtoMapper;
import com.beglyak.newproject.model.Book;
import com.beglyak.newproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookToDtoMapper mapper;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String author){
        if (author!=null){
            return bookService.findByAuthor(author);
        }
        return bookService.getAllBooks();

    }

    @PostMapping
    public void add(@RequestBody BookRequest request){
        bookService.addBook(mapper.addBookRequestToBook(request));
    }
}
