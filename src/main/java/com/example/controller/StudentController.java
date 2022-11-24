package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.ex.BookCreationException;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDTO student) {
        StudentDTO dto = studentService.saveStudent(student);
        return new ResponseEntity<>("Success add this object", HttpStatus.OK);
    }
    @GetMapping("/student")
    public ResponseEntity<?> getStudentList(){
        List<StudentDTO> studentDTOList= studentService.getStudentList();
        return  new ResponseEntity<>(studentDTOList,HttpStatus.OK);
    }
    @GetMapping("/student/{id}")
    public  ResponseEntity<?> getStudentById(@PathVariable Integer id){
        StudentDTO studentDTO=studentService.getStudentById(id);
        return new ResponseEntity<>(studentDTO,HttpStatus.OK);
    }
    @PostMapping("student/{id}")
    public  ResponseEntity<?> deleteStudentById(@PathVariable Integer id){
        StudentDTO studentDTO=studentService.deleteStudentById(id);
        return new ResponseEntity<>("Success delete",HttpStatus.OK);
    }
    @PutMapping("/student/{id}")
    public  ResponseEntity<?> putStudentById(@RequestBody StudentDTO studentDTO,@PathVariable("id") Integer id){
        studentService.putStudentById(studentDTO,id);
        return  new ResponseEntity<>("Success put on id",HttpStatus.OK);
    }

}
