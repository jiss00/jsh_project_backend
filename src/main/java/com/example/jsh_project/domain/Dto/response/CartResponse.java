package com.example.jsh_project.domain.Dto.response;

import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.CartItem;
import com.example.jsh_project.domain.Entity.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class CartResponse {

    public CartResponse() {
    }
    private List<PurchaseItem> items = new ArrayList<>();
    private List<CartItem> cartItems = new ArrayList<>();
    private List<Integer> prices = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<String> title = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();
    private List<String> merchant_uid= new ArrayList<>();


    public void add_price(Integer price){
        prices.add(price);
    }
    public void add_book(Book book){
        books.add(book);
    }
    public void add_quantity(Integer quantity){
        quantities.add(quantity);
    }



    public void add_item(PurchaseItem item){
        items.add(item);
    }
    public void add_cartItem(CartItem item){
        cartItems.add(item);
    }

    /*
    public void add_book(Book book,Integer stock){
        if(items.containsKey(book.getId())){
            Integer bookStock = items.get(book.getId()) + stock;
            items.put(book.getId(),bookStock);
        }
        else{
            items.put(book.getId(),stock);
        }
        this.price += book.getPrice();
    }

    public void remove_book(Book book,Integer stock){
        Integer bookStock = items.get(book.getId()) - stock;
        items.put(book.getId(),bookStock);
    }
    public static CartResponse getCartResponse(Long id) {
        return cartMap.get(id);
    }
        */

}
