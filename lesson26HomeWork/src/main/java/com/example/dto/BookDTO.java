package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    private Integer id;
    private String title;
    private String author;
    private LocalDate publishedYear; // yyyy-MM-dd
    // yyyy/MM/dd

}
