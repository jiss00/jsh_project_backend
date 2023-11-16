package com.example.jsh_project.domain.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "employname")
    private String employName;

    @Column(name = "phonenumber")
    private String phoneNumber;

    private String password;

    @Column(name = "score")
    private String toeic;

    @OneToOne(mappedBy = "member")
    private ShoppingList list;

    @OneToOne(mappedBy = "member")
    private ShoppingBasket orders;



}
