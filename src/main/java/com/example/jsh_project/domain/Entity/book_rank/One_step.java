package com.example.jsh_project.domain.Entity.book_rank;

import com.example.jsh_project.domain.Entity.Book;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@DiscriminatorValue("O")
@Entity
public class One_step extends Book{

}
