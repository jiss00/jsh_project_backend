package com.example.jsh_project;

import com.example.jsh_project.Service.*;

import com.example.jsh_project.domain.Dto.book.Book_600;
import com.example.jsh_project.domain.Dto.book.Book_700;
import com.example.jsh_project.domain.Dto.book.Book_800;
import com.example.jsh_project.domain.Dto.book.Book_basic;
import com.example.jsh_project.domain.Dto.response.CartResponse;
import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.Member;
import com.example.jsh_project.domain.Entity.ShoppingBasket;
import com.example.jsh_project.domain.Entity.ShoppingList;
import com.example.jsh_project.domain.Entity.book_rank.Basic;
import com.example.jsh_project.domain.Entity.book_rank.Final_step;
import com.example.jsh_project.domain.Entity.book_rank.One_step;
import com.example.jsh_project.domain.Entity.book_rank.Two_step;
import com.example.jsh_project.repository.BookRepository;
import com.example.jsh_project.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest {
    @Autowired
    MailService mailService;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    BoardService boardService;
    @Autowired
    OrderService orderService;
    @PersistenceContext
    EntityManager em;
    @Autowired
    BookService bookService;
    @Test
    @Transactional
    @Rollback(value = false)
    public void cart(){
        ShoppingBasket basket = orderService.findById(1L);
        Book book = bookService.findById(711L);

        ShoppingBasket updatedBasket = orderService.findById(1L);

        System.out.println("basket.getPrice() = " + updatedBasket.getPrice());
        System.out.println("basket.getBooks() = " + updatedBasket.getBooks());
    }

    @Test
    public void save1(){

        ShoppingBasket basket2 = new ShoppingBasket();
        basket2.setId(1L);
        orderService.save(basket2);

        ShoppingBasket basket3 = new ShoppingBasket();
        basket3.setId(2L);
        orderService.save(basket3);
    }
    @Test
    public void delete(){
        orderService.deleteAll(1L);
    }
    @Test
    public void sav1e(){
        ShoppingBasket basket = orderService.findById(1L);
        basket.setPrice(0);
        orderService.update(basket);
    }

    @Test
    public void find(){
        Book book = bookService.findById(702L);
        book.addStock(10);
        System.out.println(book.getStock());

        Book byId = bookService.findById(702L);
        System.out.println(byId.getStock());
    }
    @Test
    public void save(){
        Book_basic book_basic = new Book_basic();
        for(int i =0;i<3;i++){
            Basic basic = new Basic();
            basic.setStock(20);
            basic.setPrice(300);
            basic.setTitle(book_basic.getBook_names().get(i));
            basic.setImg(book_basic.getBook_img().get(i));
            bookService.save(basic);
        }
        Book_600 a = new Book_600();
        for(int i =0;i<3;i++){
            One_step basic = new One_step();
            basic.setStock(30);
            basic.setPrice(200);
            basic.setTitle(a.getBook_names().get(i));
            basic.setImg(a.getBook_img().get(i));
            bookService.save(basic);
        }
        Book_700 b = new Book_700();
        for(int i =0;i<3;i++){
            Two_step basic = new Two_step();
            basic.setStock(40);
            basic.setPrice(600);
            basic.setTitle(b.getBook_names().get(i));
            basic.setImg(b.getBook_img().get(i));
            bookService.save(basic);
        }
        Book_800 c = new Book_800();
        for(int i =0;i<3;i++){
            Final_step basic = new Final_step();
            basic.setStock(50);
            basic.setPrice(1500);
            basic.setTitle(c.getBook_names().get(i));
            basic.setImg(c.getBook_img().get(i));
            bookService.save(basic);
        }

    }
    @Value("${imp.api.key}")
    private String apiKey;
    @Value("${imp.api.secretkey}")
    private String secretKey;
    @Test
    public void check(){
        System.out.println(apiKey);
        System.out.println(secretKey);
    }
    @Test
    public void i(){
        CartResponse cartResponse = new CartResponse();
        Book book1 = bookService.findById(702L);
        Book book2 = bookService.findById(704L);
        Book book3 = bookService.findById(705L);

        System.out.println("cartResponse = " + cartResponse.getItems());
        System.out.println("cartResponse = " + cartResponse.getItems());
    }
    
    @Test
    public void test(){
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("1");
        a.add("1");
        System.out.println("a = " + a);
    }


    @Test
    public void a(){
    }

    }

