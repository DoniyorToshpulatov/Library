package com.example.service;

import com.example.dto.BookDTO;
import com.example.entity.BookEntity;
import com.example.ex.BookCreationException;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDTO create(BookDTO dto) {
        if(dto.getAuthor().length()<3){
            throw  new BookCreationException("Author wrong");
        }
        if(dto.getTitle().length()<3){
            throw  new BookCreationException("Title wrong");
        }
        BookEntity entity = new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setPublishedYear(dto.getPublishedYear());

        bookRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public void createAll(List<BookDTO> dtoList) {
        for (BookDTO bookDTO :dtoList) {
            if(bookDTO.getTitle().length()<3){
                throw new BookCreationException("Title wrong input!!");
            }
            if(bookDTO.getAuthor().length()<3){
                throw new BookCreationException("Author wrong input!!");
            }
        }
        dtoList.forEach(bookDTO -> create(bookDTO));
    }

    public BookDTO getById(Integer id) {

        Optional<BookEntity> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw  new BookCreationException("This object have not!!");
        }

            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.get().getId());
            bookDTO.setTitle(book.get().getTitle());
            bookDTO.setAuthor(book.get().getAuthor());
            bookDTO.setPublishedYear(book.get().getPublishedYear());
            return bookDTO;
    }

    public List<BookDTO> getBookList() {
        Iterable<BookEntity> bookList = bookRepository.findAll();
        List<BookDTO> dtoList = new LinkedList<>();
        bookList.forEach(book -> {
            BookDTO bookDTO = toDTO(book);
            dtoList.add(bookDTO);
        });
        return dtoList;
    }

    public void update(BookDTO dto, Integer id) {
       Optional<BookEntity> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw  new BookCreationException("This object have not!!");
        }
        if(dto.getTitle().length()<3){
            throw new BookCreationException("Title wrong input!!");
        }
        if(dto.getAuthor().length()<3){
            throw new BookCreationException("Author wrong input!!");
        }
            book.get().setTitle(dto.getTitle());
            book.get().setAuthor(dto.getAuthor());
            book.get().setPublishedYear(dto.getPublishedYear());
            bookRepository.save(book.get());

    }


    public BookDTO delete(Integer id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw  new BookCreationException("This object have not!!");
        }
        bookRepository.delete(book.get());
      BookDTO bookDTO=toDTO(book.get());
      return bookDTO;
    }

    public BookDTO toDTO(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setTitle(entity.getTitle());
        return dto;
    }
}
