package com.example.jsh_project.repository;

import com.example.jsh_project.domain.Dto.book.BookRequest;
import com.example.jsh_project.domain.Dto.request.BoardRequest;
import com.example.jsh_project.domain.Entity.Board;
import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.book_rank.Basic;
import com.example.jsh_project.domain.Entity.book_rank.Final_step;
import com.example.jsh_project.domain.Entity.book_rank.One_step;
import com.example.jsh_project.domain.Entity.book_rank.Two_step;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class BookRepository {
    @PersistenceContext
    EntityManager em;
    public void save(Book book){
        em.persist(book);
    }

    public void save_book(Book book) {
        em.persist(book);
    }

    public List<BookRequest> findAll(String type) {
            if(type.equals("B")){
                List<Basic> list = em.createQuery("select b from Basic b", Basic.class).getResultList();
                List<BookRequest> requests = new ArrayList<>();
                for (Book basic : list) {
                    BookRequest response = new BookRequest();
                    response.setTitle(basic.getTitle());
                    response.setImg(basic.getImg());
                    response.setId(basic.getId());
                    response.setContent(basic.getContent());
                    response.setStock(basic.getStock());
                    response.setPrice(basic.getPrice());
                    requests.add(response);
                }
                return requests;
            }
            else if(type.equals("O")){
                List<One_step> list = em.createQuery("select b from One_step b", One_step.class).getResultList();
                List<BookRequest> requests = new ArrayList<>();
                for (Book basic : list) {

                    BookRequest response = new BookRequest();
                    response.setTitle(basic.getTitle());
                    response.setImg(basic.getImg());
                    response.setId(basic.getId());
                    response.setContent(basic.getContent());
                    response.setStock(basic.getStock());
                    response.setPrice(basic.getPrice());
                    requests.add(response);
                }
                return requests;
            }
            else if(type.equals("T")){
                List<Two_step> list = em.createQuery("select b from Two_step b", Two_step.class).getResultList();
                List<BookRequest> requests = new ArrayList<>();
                for (Book basic : list) {

                    BookRequest response = new BookRequest();
                    response.setTitle(basic.getTitle());
                    response.setImg(basic.getImg());
                    response.setId(basic.getId());
                    response.setContent(basic.getContent());
                    response.setStock(basic.getStock());
                    response.setPrice(basic.getPrice());
                    requests.add(response);
                }
                return requests;
            }
            else if(type.equals("F")){
                List<Final_step> list = em.createQuery("select b from Final_step b", Final_step.class).getResultList();
                List<BookRequest> requests = new ArrayList<>();
                for (Book basic : list) {

                    BookRequest response = new BookRequest();
                    response.setTitle(basic.getTitle());
                    response.setImg(basic.getImg());
                    response.setId(basic.getId());
                    response.setContent(basic.getContent());
                    response.setStock(basic.getStock());
                    response.setPrice(basic.getPrice());
                    requests.add(response);
                }
                return requests;
            }
            else return null;

    }

    public Book findById(Long id) {
        return em.find(Book.class,id);
    }

    public void remove(Book book) {
        em.remove(book);
    }

    public void update(Book book) {
        em.merge(book);
    }
}
