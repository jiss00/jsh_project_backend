package com.example.jsh_project.Service;

import com.example.jsh_project.domain.Dto.book.BookRequest;
import com.example.jsh_project.domain.Dto.request.BoardRequest;
import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.book_rank.Basic;
import com.example.jsh_project.domain.Entity.book_rank.Final_step;
import com.example.jsh_project.domain.Entity.book_rank.One_step;
import com.example.jsh_project.domain.Entity.book_rank.Two_step;
import com.example.jsh_project.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void save_book(Book book){
        bookRepository.save_book(book);
    }
    @Transactional
    public void update(Book book){
        bookRepository.update(book);
    }
    public List<BookRequest> findAll(String type){
            return bookRepository.findAll(type);
    }
    public Book findById(Long id){
        return bookRepository.findById(id);
    }
    //책 이름으로 책id 반환받기
    public Long findByName(String title){
        return bookRepository.findByName(title);
    }
    @Transactional
    public void delete(Long id){
        Book book = findById(id);
        bookRepository.remove(book);
    }
}


