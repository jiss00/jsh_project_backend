package com.example.jsh_project.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
public class ShoppingList {
    @Id
    private Long id;
    @Column(name = "price")
    private Integer price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToMany(mappedBy = "shoppingList",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PurchaseItem> items = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "shoppingList",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public void add_price(Integer price) {
        if (this.price == null) {
            this.price = price;
        } else {
            this.price += price;
        }
    }
    public void remove_price(Integer price){
        this.price -= price;
    }

    public void addBooks(Book book) {
        book.setShoppingList(this);
        books.add(book);
    }
    public void addItems(PurchaseItem item){
        items.add(item);
        item.setShoppingList(this);
    }
}
