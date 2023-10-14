package com.example.jsh_project.controller;

import com.example.jsh_project.Service.MailService;
import com.example.jsh_project.Service.MemberService;
import com.example.jsh_project.domain.Dto.request.AuthNumber;
import com.example.jsh_project.domain.Dto.request.MailSend;
import com.example.jsh_project.jwt.GetClaim;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
@CrossOrigin(origins = "http://localhost:3000" )
public class MailController {
    private final MailService mailService;
    private final MemberService memberService;
    private final GetClaim getClaim;
    @Value("${jwt.token.secret}")
    private String secretKey;

    @PostMapping("")
    public String MailSend(@RequestBody MailSend email, HttpSession session){
        String num = mailService.sendMail(email.getEmail());

        session.setAttribute("number",num);
        session.setAttribute("email",email.getEmail());
        return num;
    }

    @PostMapping("/getPwd")
    String getPwd(@RequestBody AuthNumber authNumber, HttpSession session){
        String tryNumber = (String) session.getAttribute("number");
        String email = (String)session.getAttribute("email");
        if(authNumber.getNum().equals(tryNumber)){
            String pwd = memberService.getPwd(email);
            return pwd;
        }
        else{
            return "fail";
        }
    }
}
