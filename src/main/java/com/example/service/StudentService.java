package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.ex.BookCreationException;
import com.example.repository.StudentRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO saveStudent(StudentDTO dto) {
        if (dto.getSurname().trim().length() < 3) {
            throw new BookCreationException("Surname wrong input!!!");
        }
        if (dto.getName().trim().length() < 3) {
            throw new BookCreationException("Name wrong input!!!!");
        }
        if (dto.getPhone().trim().length() < 3) {
            throw new BookCreationException("Phone wrong input");
        }
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(LocalDate.now());

        studentRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<StudentDTO> getStudentList() {
        Iterable<StudentEntity> studentEntityList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new LinkedList<>();
        for (StudentEntity st : studentEntityList) {
            StudentDTO studentDTO = toDTO(st);
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;
    }

    public StudentDTO getStudentById(Integer id) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if (studentEntity.isEmpty()) {
            throw new BookCreationException("Is not Object!!");
        }
        StudentDTO studentDTO = toDTO(studentEntity.get());

        return studentDTO;
    }

    public StudentDTO deleteStudentById(Integer id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            throw new BookCreationException("Have not object!!!!");
        }
        studentRepository.delete(student.get());
        StudentDTO studentDTO = toDTO(student.get());
        return studentDTO;
    }

    public StudentDTO toDto(StudentEntity studentEntity) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(studentEntity.getName());
        studentDTO.setSurname(studentEntity.getSurname());
        studentDTO.setId(studentEntity.getId());
        studentDTO.setCreatedDate(studentEntity.getCreatedDate());
        return studentDTO;
    }

    public StudentEntity toEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        // entity.setCreatedDate(LocalDate.now());
        return entity;
    }

    public StudentDTO putStudentById(StudentDTO studentDTO, Integer id) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(id);
        if (studentEntity.isEmpty()) {
            throw new BookCreationException(" Is not object by id!!");
        }
        if (studentDTO.getPhone().trim().length() < 3) {
            throw new BookCreationException("Phone is not wrong input!!!");
        }
        if (studentDTO.getName().trim().length() < 3) {
            throw new BookCreationException("Name is not wrong input!!");
        }
        if (studentDTO.getSurname().trim().length() < 3) {
            throw new BookCreationException("Surname is wrong input!!!");
        }

        StudentEntity student = toEntity(studentDTO);
        student.setId(id);
        studentRepository.save(student);
        return studentDTO;
    }

    public StudentDTO toDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        return dto;
    }
}
