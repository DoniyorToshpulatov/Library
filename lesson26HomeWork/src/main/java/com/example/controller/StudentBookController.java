package com.example.controller;


import com.example.dto.BookDTO;
import com.example.dto.StudentBookDTO;
import com.example.service.BookService;
import com.example.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentBookController {

    @Autowired
    private StudentBookService studentBookService;

    @PostMapping("/student_book")
    public StudentBookDTO takeBook(@RequestBody StudentBookDTO dto) {
        return studentBookService.create(dto);
    }


    @GetMapping("/student_book/{id}")
    public StudentBookDTO takeBook(@PathVariable("id") Integer sbId) {
        return studentBookService.getById(sbId);
    }

    @GetMapping("/student_book/{id}/full")
    public StudentBookDTO takeBookFull(@PathVariable("id") Integer sbId) {
        return studentBookService.getFullDetailById(sbId);
    }
}
