package com.example.jsh_project.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {
    public PurchaseItem(ShoppingList shoppingList, Book book, Integer quantity,String merchant_uid) {
        this.shoppingList = shoppingList;
        this.book = book;
        this.quantity = quantity;
        this.merchant_uid = merchant_uid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "list_id")
    private ShoppingList shoppingList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity")
    private Integer quantity;
    @Column(name="merchant_uid")
    private String merchant_uid;

    public Integer remove_stock(Integer stock){
        return this.quantity-=stock;
    }


}
