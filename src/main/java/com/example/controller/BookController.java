package com.example.controller;

import com.example.dto.BookDTO;
import com.example.ex.BookCreationException;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.XmlList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book_all")
    public ResponseEntity<?> createBook(@RequestBody List<BookDTO> dtoList) {
        bookService.createAll(dtoList);
        return new ResponseEntity<>("Success add all",HttpStatus.OK);
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") Integer id) {

        BookDTO bookDTO= bookService.getById(id);
        return new ResponseEntity<>(bookDTO,HttpStatus.OK);
    }


    @GetMapping("/book")
    public ResponseEntity<?> getBookList() {
        List<BookDTO> bookDTOList= bookService.getBookList();
        return new ResponseEntity<>(bookDTOList,HttpStatus.OK);
    }


    @PutMapping("/book/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDTO, @PathVariable("id") Integer id) {
        bookService.update(bookDTO, id);
        return new ResponseEntity<>("Success update ",HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Integer id) {
        BookDTO result=bookService.delete(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO){
        BookDTO result=bookService.create(bookDTO);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }


}
