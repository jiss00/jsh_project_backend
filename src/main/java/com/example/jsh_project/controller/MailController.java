package com.example.jsh_project.controller;

import com.example.jsh_project.Service.MailService;
import com.example.jsh_project.Service.MemberService;
import com.example.jsh_project.domain.Dto.request.AuthNumber;
import com.example.jsh_project.domain.Dto.request.MailSend;
import com.example.jsh_project.domain.Dto.request.SessionModel;
import com.example.jsh_project.jwt.GetClaim;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
@CrossOrigin(origins = "*" )

public class MailController {
    private final MailService mailService;
    private final MemberService memberService;
    private final GetClaim getClaim;
    private String auth;
    @Value("${jwt.token.secret}")
    private String secretKey;

    @PostMapping("")
    public String MailSend(@RequestBody MailSend email, HttpServletRequest request){
        String num = mailService.sendMail(email.getEmail());
        auth = num;
        System.out.println("auth = " + auth);
        return num;
    }
    @PostMapping("/confirm")
    public String Confirm(@RequestBody MailSend email, HttpServletRequest request){
        if (email.getEmail().equals(auth)) {
            return "성공";
        } else if (auth == null) {
            return "null 값";
        } else {
            return auth;
        }
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
