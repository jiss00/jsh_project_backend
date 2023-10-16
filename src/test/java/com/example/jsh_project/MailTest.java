package com.example.jsh_project;

import com.example.jsh_project.Service.BoardService;
import com.example.jsh_project.Service.BookService;
import com.example.jsh_project.Service.MailService;
import com.example.jsh_project.domain.Dto.book.*;
import com.example.jsh_project.domain.Entity.Board;
import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.book_rank.Basic;
import com.example.jsh_project.domain.Entity.book_rank.Final_step;
import com.example.jsh_project.domain.Entity.book_rank.One_step;
import com.example.jsh_project.domain.Entity.book_rank.Two_step;
import com.example.jsh_project.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest {
    @Autowired
    MailService mailService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BoardService boardService;
    @PersistenceContext
    EntityManager em;
    @Autowired
    BookService bookService;

    @Test
    public void test(){
        Book_800 book_basic = new Book_800();
        Book book = new Book();
        bookService.save_book(book);
        for(int i=0; i<3; i++){
            Final_step basic = new Final_step();
            basic.setTitle(book_basic.getBook_names().get(i));
            basic.setImg(book_basic.getBook_img().get(i));
            basic.setStock("30");
            basic.setContent(null);
            basic.setPrice("20");
            basic.setBook(book);
            bookService.save(basic);
        }
    }
    @Test
    public void a(){
        String[] strings = bookService.findAll(1L).toArray(new String[0]);
        System.out.println(strings);
    }



    }

