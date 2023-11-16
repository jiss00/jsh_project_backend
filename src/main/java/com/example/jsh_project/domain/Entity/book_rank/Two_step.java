package com.example.jsh_project.domain.Entity.book_rank;

import com.example.jsh_project.domain.Entity.Book;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@DiscriminatorValue("T")
@Entity
public class Two_step extends Book {

}
