package com.example.jsh_project.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Entity
public abstract class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Integer price;
    private String img;
    //남아 있는 수량
    private Integer stock;
    private Integer purchase;
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<PurchaseItem> items;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<CartItem> carts_items;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ShoppingList shoppingList;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private ShoppingBasket shoppingBasket;


    public void addStock(Integer quantity){
        this.stock += quantity;
    }
    public void removeStock(Integer quantity){
        Integer mount = this.stock - quantity;
        if(mount < 0){
            throw new RuntimeException("수량이 0 보다 낮습니다.");
        }
        this.stock = mount;
    }




}
