package com.example.jsh_project.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
   private final JavaMailSender javaMailSender;
    SimpleMailMessage message = new SimpleMailMessage();
    private static int number;

    public String sendMail(String email){
        message.setSubject("인증번호가 발신되었습니다.");
        message.setTo(email);
        String number1 = createNumber();
        message.setText(number1);
        javaMailSender.send(message);
        return number1;
    }

    public String createNumber(){
        number = (int)(Math.random()*(90000)) + 100000;
        return String.valueOf(number);
    }

}
