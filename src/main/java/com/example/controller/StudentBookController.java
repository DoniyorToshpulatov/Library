package com.example.controller;


import com.example.dto.BookDTO;
import com.example.dto.StudentBookDTO;
import com.example.ex.BookCreationException;
import com.example.service.BookService;
import com.example.service.StudentBookService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentBookController {

    @Autowired
    private StudentBookService studentBookService;

    @PostMapping("/student_book")
    public ResponseEntity<?> takeBook(@RequestBody StudentBookDTO dto) {
        studentBookService.create(dto);
        return new ResponseEntity<>("Success add", HttpStatus.OK);
    }


    @GetMapping("/student_book/{id}")
    public ResponseEntity<?> takeBook(@PathVariable("id") Integer sbId) {
        StudentBookDTO studentBookDTO=studentBookService.getById(sbId);
        return new  ResponseEntity<>(studentBookDTO,HttpStatus.OK);
    }

    @GetMapping("/student_book/{id}/full")
    public ResponseEntity<?> takeBookFull(@PathVariable("id") Integer sbId) {
        StudentBookDTO studentBookDTO=studentBookService.getFullDetailById(sbId);
        return  new ResponseEntity<>(studentBookDTO,HttpStatus.OK);
    }
    @GetMapping("/student_book")
    public  ResponseEntity<?> getAll(){
        List<StudentBookDTO> studentBookDTOS=studentBookService.getAllList();
        return new ResponseEntity<>(studentBookDTOS,HttpStatus.OK);
    }

}
