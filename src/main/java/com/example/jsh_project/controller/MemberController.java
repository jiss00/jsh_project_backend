package com.example.jsh_project.controller;

import com.example.jsh_project.Service.MailService;
import com.example.jsh_project.Service.MemberService;
import com.example.jsh_project.domain.Dto.book.Book_600;
import com.example.jsh_project.domain.Dto.book.Book_700;
import com.example.jsh_project.domain.Dto.book.Book_800;
import com.example.jsh_project.domain.Dto.book.Book_basic;
import com.example.jsh_project.domain.Dto.request.*;
import com.example.jsh_project.domain.Dto.response.MemberLoginResponse;
import com.example.jsh_project.domain.Dto.response.MemberRegisterResponse;
import com.example.jsh_project.domain.Dto.response.RecommendResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import java.io.IOException;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;
    @PostMapping("/email")
    public void mail(@RequestBody MailSend email, HttpServletResponse response){
        String authNumber = mailService.sendMail(email.getEmail());
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName("authNumberCookie");
        cookieGenerator.addCookie(response, authNumber);
    }

    @PostMapping("/join")
    public ResponseEntity<MemberRegisterResponse> register(@RequestBody MemberRegisterRequest memberRegisterRequest, HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("authNumberCookie".equals(cookie.getName())) {
                        if(!cookie.getValue().equals(memberRegisterRequest.getConfim())){
                            System.out.println("에러뜨는거 정상");
                            throw new RuntimeException();
                        }

                    }}}
            MemberDto memberDto = memberService.register(memberRegisterRequest);
            return new ResponseEntity<>(new MemberRegisterResponse(memberDto.getEmployName(),memberDto.getEmail(),memberDto.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        try {
            String token = memberService.login(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());

            return new ResponseEntity<>(new MemberLoginResponse(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/recommend")
    public ResponseEntity<RecommendResponse> recommend(@RequestBody Member_recommend member) throws IOException {
        String score = member.getScore();
        if(score.equals("basic")){
            Book_basic book = new Book_basic();
            try {
                return new ResponseEntity<>(new RecommendResponse(book.getBook_names().toArray(new String[0]),
                        book.getBook_img().toArray(new String[0]),book.getId().toArray(new String[0])), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        else if(score.equals("600 ~")){
            Book_600 book = new Book_600();
            try {
                return new ResponseEntity<>(new RecommendResponse(book.getBook_names().toArray(new String[0]),
                        book.getBook_img().toArray(new String[0]),book.getId().toArray(new String[0])), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        else if(score.equals("700 ~")){
            Book_700 book = new Book_700();
            try {
                return new ResponseEntity<>(new RecommendResponse(book.getBook_names().toArray(new String[0]),
                        book.getBook_img().toArray(new String[0]),book.getId().toArray(new String[0])), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }        }

        else if(score.equals("850 ~")){
            Book_800 book = new Book_800();
            try {
                return new ResponseEntity<>(new RecommendResponse(book.getBook_names().toArray(new String[0]),
                        book.getBook_img().toArray(new String[0]),book.getId().toArray(new String[0])), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }        }
        else{
            return null;
        }
    }
}
