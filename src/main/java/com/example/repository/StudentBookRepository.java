package com.example.repository;

import ch.qos.logback.core.status.Status;
import com.example.entity.BookEntity;
import com.example.entity.StudentBookEntity;
import com.example.entity.StudentEntity;
import com.example.enums.StudentBookStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentBookRepository extends CrudRepository<StudentBookEntity,Integer> {
  //  Iterable<StudentEntity> findByBookIdAndStatus(Integer bookId, StudentBookStatus status);
  //  List<BookEntity> findByStudentIdAndStatus(Integer studentId, StudentBookStatus status);
}
