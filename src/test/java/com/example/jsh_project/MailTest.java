package com.example.jsh_project;

import com.example.jsh_project.Service.MailService;
import com.example.jsh_project.domain.Dto.book.Book_basic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailTest {
    @Autowired
    MailService mailService;


    public MailTest() {
    }
    @Test
    public void mail(){
        mailService.sendMail("kuy09085@naver.com");
    }



}
