package com.example.service;

import com.example.dto.BookDTO;
import com.example.dto.StudentBookDTO;
import com.example.dto.StudentDTO;
import com.example.entity.BookEntity;
import com.example.entity.StudentBookEntity;
import com.example.entity.StudentEntity;
import com.example.enums.StudentBookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentBookService {
    @Autowired
    private StudentBookRepository studentBookRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentService studentService;

    public StudentBookDTO create(StudentBookDTO dto) {
        StudentEntity student = studentRepository.getById(dto.getStudentId());
        BookEntity book = bookRepository.getById(dto.getBookId());
        // checking

        StudentBookEntity studentBook = new StudentBookEntity();
        studentBook.setBook(book);
        studentBook.setStudent(student);
        studentBook.setDurationDay(dto.getDurationDay());
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBook.setStatus(StudentBookStatus.TAKEN);
        studentBookRepository.create(studentBook);

        dto.setId(studentBook.getId());
        return dto;
    }

    public StudentBookDTO getById(Integer sbId) {
        StudentBookEntity studentBook = studentBookRepository.getById(sbId);


        StudentBookDTO dto = new StudentBookDTO();
        dto.setId(studentBook.getId());
        dto.setBookId(studentBook.getBook().getId());
        dto.setStudentId(studentBook.getStudent().getId());
        dto.setDurationDay(studentBook.getDurationDay());
        dto.setCreatedDate(studentBook.getCreatedDate());
        dto.setReturnedDate(studentBook.getReturnedDate());
        dto.setStatus(studentBook.getStatus());
        return dto;
    }

    public StudentBookDTO getFullDetailById(Integer sbId) {
        StudentBookEntity studentBook = studentBookRepository.getById(sbId);


        StudentBookDTO dto = new StudentBookDTO();
        dto.setId(studentBook.getId());

        dto.setBook(bookService.toDTO(studentBook.getBook()));
        dto.setStudent(studentService.toDTO(studentBook.getStudent()));

        dto.setDurationDay(studentBook.getDurationDay());
        dto.setCreatedDate(studentBook.getCreatedDate());
        dto.setReturnedDate(studentBook.getReturnedDate());
        dto.setStatus(studentBook.getStatus());
        return dto;
    }
}
