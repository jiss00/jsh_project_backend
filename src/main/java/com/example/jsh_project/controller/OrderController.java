package com.example.jsh_project.controller;

import com.example.jsh_project.Service.BookService;
import com.example.jsh_project.Service.MemberService;
import com.example.jsh_project.Service.OrderService;
import com.example.jsh_project.Service.PurchaseService;
import com.example.jsh_project.domain.Dto.book.BookRequest;
import com.example.jsh_project.domain.Dto.request.CancelRequest;
import com.example.jsh_project.domain.Dto.request.CartRequest;
import com.example.jsh_project.domain.Dto.request.Member_recommend;
import com.example.jsh_project.domain.Dto.response.CartResponse;
import com.example.jsh_project.domain.Dto.response.MemberLoginResponse;
import com.example.jsh_project.domain.Dto.response.ViewPurchase;
import com.example.jsh_project.domain.Entity.*;
import com.example.jsh_project.repository.OrderRepository;
import com.example.jsh_project.repository.PurchaseRepository;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://jshtoy.netlify.app")
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final MemberService memberService;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;
    private final BookService bookService;

    @PostMapping("/cart")
    public CartResponse cart(@RequestBody CartRequest request ) throws IOException {
        try {
            Book book = bookService.findById(request.getBook_id());
            CartResponse cartResponse = new CartResponse();
            ShoppingBasket basket = orderService.cart(request.getMember_id(), book, request.getStock());
            for(int i =0;i<basket.getItems().size();i++){
                cartResponse.add_cartItem(basket.getItems().get(i));
            }
            return cartResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @CrossOrigin(origins = "https://jshtoy.netlify.app/")
    @GetMapping("/cart")
    public ViewPurchase cart(@RequestParam Long id) throws IOException {
        try {
            ViewPurchase view = new ViewPurchase();
            List<CartItem> item = orderService.findItems(id);
            view.setPrice(orderService.findById(id).getPrice());
            for (int i = 0; i < item.size(); i++) {
                view.getTitles().add(item.get(i).getBook().getTitle());
                view.getImgs().add(item.get(i).getBook().getImg());
                view.getStock().add(item.get(i).getQuantity());
                view.getPrices().add(item.get(i).getBook().getPrice());
                view.getId().add(item.get(0).getId());
                view.getBooks().add(item.get(i).getBook().getId());
            }
            return view;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @CrossOrigin(origins = "https://jshtoy.netlify.app/")
    @PostMapping("/purchase")
    //하나의 책에 대해서 구매하는 것임.
    public CartResponse purchase(@RequestBody CartRequest request ) throws IOException {
        try {
            Book book = bookService.findById(request.getBook_id());
            CartResponse cartResponse = new CartResponse();
            ShoppingList list = orderService.purchase(request.getMember_id(), book, request.getStock(),request.getWhere(),request.getMerchant_id());
            for(int i =0;i<list.getItems().size();i++){
                cartResponse.add_item(list.getItems().get(i));
            }

            return cartResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "https://jshtoy.netlify.app/")
    @GetMapping("/purchase")
    public ViewPurchase purchase(@RequestParam Long id) throws IOException{
        try{
            ViewPurchase view = new ViewPurchase();
            List<PurchaseItem> item = purchaseService.findItems(id);
            view.setPrice(purchaseRepository.findById(id).getPrice());
            for(int i= item.size()-1;i>=0;i--){
                view.getTitles().add(item.get(i).getBook().getTitle());
                view.getImgs().add(item.get(i).getBook().getImg());
                view.getStock().add(item.get(i).getQuantity());
                view.getPrices().add(item.get(i).getBook().getPrice());
                view.getId().add(item.get(i).getId());
                view.getMerchant_uid().add(item.get(i).getMerchant_uid());

            }
            return view;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @PostMapping("/cancel")
    @CrossOrigin(origins = "https://jshtoy.netlify.app/")
    public void cancel(@RequestBody CancelRequest request){
        try{
            ViewPurchase view = new ViewPurchase();
            orderService.cancel(request.getMember_id(),request.getItemId(),request.getStock());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @PostMapping("/findBook")
    public Book getBookId(@RequestBody Member_recommend book){
        try{
            Long id = bookService.findByName(book.getScore());
            return bookService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }



}
