package com.example.controller;

import com.example.dto.BookDTO;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.XmlList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }


    @PostMapping("/book_all")
    public void createBook(@RequestBody List<BookDTO> dtoList) {
        bookService.createAll(dtoList);
    }


    @GetMapping("/book/{id}")
    public BookDTO getBookById(@PathVariable("id") Integer id) {
        return bookService.getById(id);
    }


    @GetMapping("/book")
    public List<BookDTO> getBookList() {
        return bookService.getBookList();
    }


    @PutMapping("/book/{id}")
    public void updateBook(@RequestBody BookDTO bookDTO, @PathVariable("id") Integer id) {
        bookService.update(bookDTO, id);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookService.delete(id);
    }
}
