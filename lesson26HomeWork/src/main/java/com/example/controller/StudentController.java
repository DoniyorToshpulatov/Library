package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public StudentDTO saveStudent(@RequestBody StudentDTO student) {
        StudentDTO dto = studentService.saveStudent(student);
        return dto;
    }

   /* @GetMapping("/student")
    public List<Student> getStudentList() {
        List<Student> studentList = studentService.getStudentList();
        if (studentList.isEmpty()) {
            return null;
        }
        return studentList;
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        System.out.println(student);
        return student;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable Integer id) {
        String delete = studentService.deleteStudentById(id);
        System.out.println(delete);
        return delete;
    }

    @PutMapping("/student/{id}")
    public String putStudentById(@RequestBody StudentDTO student, @PathVariable Integer id) {
        Student studentById = getStudentById(id);
        if (studentById==null) {
            return "Student not found";
        }

        student.setId(id);
        String update = studentService.updateStudent(student);
        return update;
    }*/
}
