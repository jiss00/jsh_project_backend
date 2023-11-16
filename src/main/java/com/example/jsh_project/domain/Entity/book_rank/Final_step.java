package com.example.jsh_project.domain.Entity.book_rank;

import com.example.jsh_project.domain.Entity.Book;
import jakarta.persistence.*;
import lombok.*;


@DiscriminatorValue("F")
@Getter
@Setter
@Entity
public class Final_step extends Book {

}
