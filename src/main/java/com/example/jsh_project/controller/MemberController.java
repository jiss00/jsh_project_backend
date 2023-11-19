package com.example.jsh_project.controller;

import com.example.jsh_project.Service.*;
import com.example.jsh_project.domain.Dto.book.*;
import com.example.jsh_project.domain.Dto.request.*;
import com.example.jsh_project.domain.Dto.response.MemberLoginResponse;
import com.example.jsh_project.domain.Dto.response.MemberRegisterResponse;
import com.example.jsh_project.domain.Entity.ShoppingBasket;
import com.example.jsh_project.domain.Entity.ShoppingList;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "https://jshtoy.netlify.app/")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MailService mailService;
    private final BookService bookService;
    private final OrderService orderService;
    private final PurchaseService purchaseService;

    @PostMapping("/email")
    public void mail(@RequestBody MailSend email, HttpServletResponse response) {
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
                        if (!cookie.getValue().equals(memberRegisterRequest.getConfim())) {
                            System.out.println("에러뜨는거 정상");
                            throw new RuntimeException();
                        }

                    }
                }
            }
            MemberDto memberDto = memberService.register(memberRegisterRequest);
            ShoppingBasket basket = new ShoppingBasket();
            ShoppingList list = new ShoppingList();

            basket.setId(memberDto.getId());
            list.setId(memberDto.getId());

            orderService.save(basket);
            purchaseService.save(list);
            return new ResponseEntity<>(new MemberRegisterResponse(memberDto.getEmployName(), memberDto.getEmail(), memberDto.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody MemberLoginRequest memberLoginRequest) {
        try {
            String token = memberService.login(memberLoginRequest.getEmail(), memberLoginRequest.getPassword());
            Long memberId = memberService.findByEmail(memberLoginRequest.getEmail());

            return new ResponseEntity<>(new MemberLoginResponse(token,memberId), HttpStatus.OK);
        }catch (RuntimeException e) {
            // RuntimeException이 발생하면 해당 예외 메시지를 클라이언트에게 전달
            return new ResponseEntity<>(new MemberLoginResponse(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recommend")
    public ResponseEntity<List<BookRequest>> recommend(@RequestBody Member_recommend member) throws IOException {
        String score = member.getScore();
        if (score.equals("basic")) {
            List<BookRequest> all = bookService.findAll("B");
            try {
                return new ResponseEntity<>(all, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else if (score.equals("600 ~")) {
            List<BookRequest> all = bookService.findAll("O");
            try {
                return new ResponseEntity<>(all, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else if (score.equals("700 ~")) {
            List<BookRequest> all = bookService.findAll("T");
            try {
                return new ResponseEntity<>(all, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else if (score.equals("850 ~")) {
            List<BookRequest> all = bookService.findAll("F");
            try {
                return new ResponseEntity<>(all, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return null;
        }
    }
}
