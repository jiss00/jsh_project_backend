package com.example.jsh_project.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
public class ShoppingBasket {
    @Id
    private Long id;
    @Column(name = "price")
    private Integer price; //장바구니 누적금액
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "shoppingBasket",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> items = new ArrayList<>();
    @OneToMany(mappedBy = "shoppingBasket", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books = new ArrayList<>();

    public void remove_price(Integer price) {
        this.price -= price;
    }
    public void add_price(Integer price) {
        if (this.price == null) {
            this.price = price;
        } else {
            this.price += price;
        }
    }
    public void addBooks(Book book) {
        book.setShoppingBasket(this);
        books.add(book);
    }
    public void addItems(CartItem item){
        items.add(item);
        item.setShoppingBasket(this);
    }
    public void removeItems(CartItem item) {
        items.remove(item);
        item.setShoppingBasket(null); // Remove the association to avoid orphaned entities
    }

}
