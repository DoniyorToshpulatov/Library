package com.example.dto;

import com.example.enums.StudentBookStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentBookDTO {
    private Integer id;
    private Integer studentId;
    private Integer bookId;
    private Integer durationDay;

    private StudentDTO student;
    private BookDTO book;

    private LocalDateTime createdDate;
    private StudentBookStatus status;
    private LocalDateTime returnedDate;
}
