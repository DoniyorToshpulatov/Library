package com.example.service;

import ch.qos.logback.core.status.Status;
import com.example.dto.StudentBookDTO;
import com.example.dto.StudentDTO;
import com.example.entity.BookEntity;
import com.example.entity.StudentBookEntity;
import com.example.entity.StudentEntity;
import com.example.enums.StudentBookStatus;
import com.example.ex.BookCreationException;
import com.example.repository.BookRepository;
import com.example.repository.StudentBookRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        Optional<StudentEntity> student = studentRepository.findById(dto.getStudentId());
        Optional<BookEntity> book = bookRepository.findById(dto.getBookId());
        if (student.isEmpty()) {
            throw new BookCreationException("Is not Student");
        }
        if (book.isEmpty()) {
            throw new BookCreationException("Is not book!!");
        }
        StudentBookEntity studentBook = new StudentBookEntity();
        studentBook.setBook(book.get());
        studentBook.setStudent(student.get());
        studentBook.setDurationDay(dto.getDurationDay());
        studentBook.setCreatedDate(LocalDateTime.now());
        studentBook.setStatus(StudentBookStatus.TAKEN);
        studentBookRepository.save(studentBook);

        dto.setId(studentBook.getId());
        return dto;
    }

    public StudentBookDTO getById(Integer sbId) {
        Optional<StudentBookEntity> studentBook = studentBookRepository.findById(sbId);

        if (studentBook.isEmpty()) {
            throw new BookCreationException("Is not exist!!");
        }

        StudentBookDTO dto = new StudentBookDTO();
        dto.setId(studentBook.get().getId());
        dto.setBookId(studentBook.get().getBook().getId());
        dto.setStudentId(studentBook.get().getStudent().getId());
        dto.setDurationDay(studentBook.get().getDurationDay());
        dto.setCreatedDate(studentBook.get().getCreatedDate());
        dto.setReturnedDate(studentBook.get().getReturnedDate());
        dto.setStatus(studentBook.get().getStatus());
        return dto;
    }

    public StudentBookDTO getFullDetailById(Integer sbId) {
        Optional<StudentBookEntity> studentBook = studentBookRepository.findById(sbId);
        if (studentBook.isEmpty()) {
            throw new BookCreationException("Is not exist!!");
        }


        StudentBookDTO dto = new StudentBookDTO();
        dto.setId(studentBook.get().getId());

        dto.setBook(bookService.toDTO(studentBook.get().getBook()));
        dto.setStudent(studentService.toDTO(studentBook.get().getStudent()));

        dto.setDurationDay(studentBook.get().getDurationDay());
        dto.setCreatedDate(studentBook.get().getCreatedDate());
        dto.setReturnedDate(studentBook.get().getReturnedDate());
        dto.setStatus(studentBook.get().getStatus());
        return dto;
    }
    public List<StudentBookDTO> getAllList(){
        Iterable<StudentBookEntity> studentBookEntities=studentBookRepository.findAll();
        List<StudentBookDTO> studentBookDTOS=new LinkedList<>();
        for (StudentBookEntity st:studentBookEntities) {
            StudentBookDTO studentBookDTO=new StudentBookDTO();

            studentBookDTO.setBook(bookService.toDTO(st.getBook()));
            studentBookDTO.setStudent(studentService.toDTO(st.getStudent()));

            studentBookDTO.setDurationDay(st.getDurationDay());
            studentBookDTO.setCreatedDate(st.getCreatedDate());
            studentBookDTO.setReturnedDate(st.getReturnedDate());
            studentBookDTO.setStatus(st.getStatus());
            studentBookDTOS.add(studentBookDTO);
        }
        return studentBookDTOS;
    }
   /* public List<StudentDTO> getStudentByBookId(Integer bookId){
        Iterable<StudentEntity> studentEntityList=studentBookRepository.findByBookIdAndStatus(bookId, StudentBookStatus.TAKEN);
         List<StudentDTO> studentDTOList=new LinkedList<>();
        for (StudentEntity st:studentEntityList) {
            StudentDTO studentDTO=new StudentDTO();
            studentDTO.setSurname(st.getSurname());
            studentDTO.setName(st.getName());
            studentDTO.setPhone(st.getPhone());
            studentDTO.setCreatedDate(st.getCreatedDate());
            studentDTO.setId(st.getId());
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }*/
   }
