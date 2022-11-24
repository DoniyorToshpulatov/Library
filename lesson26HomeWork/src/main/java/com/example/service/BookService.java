package com.example.service;

import com.example.dto.BookDTO;
import com.example.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDTO create(BookDTO dto) {
        // checking
        BookEntity entity = new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setPublishedYear(dto.getPublishedYear());

        bookRepository.create(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public void createAll(List<BookDTO> dtoList) {
        dtoList.forEach(bookDTO -> create(bookDTO));
    }

    public BookDTO getById(Integer id) {
        BookEntity book = bookRepository.getById(id);
        if (book != null) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setPublishedYear(book.getPublishedYear());
            return bookDTO;
        }
        return null;
    }

    public List<BookDTO> getBookList() {
        List<BookEntity> bookList = bookRepository.getAll();
        List<BookDTO> dtoList = new LinkedList<>();

        bookList.forEach(book -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setPublishedYear(book.getPublishedYear());
            dtoList.add(bookDTO);
        });
        return dtoList;
    }

    public void update(BookDTO dto, Integer id) {
        BookEntity book = bookRepository.getById(id);
        if (book != null) {
            book.setTitle(dto.getTitle());
            book.setAuthor(dto.getAuthor());
            book.setPublishedYear(dto.getPublishedYear());
            bookRepository.update(book);
        }
    }


    public void delete(Integer id) {
        BookEntity book = bookRepository.getById(id);
        if (book != null) {
            bookRepository.delete(id);
        }
    }

    public BookDTO toDTO(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setTitle(entity.getTitle());
        return dto;
    }
}
