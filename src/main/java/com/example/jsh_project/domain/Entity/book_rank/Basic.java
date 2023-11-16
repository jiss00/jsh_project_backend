package com.example.jsh_project.domain.Entity.book_rank;

import com.example.jsh_project.domain.Entity.Book;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;


@DiscriminatorValue("B")
@Getter
@Setter
@Entity
public class Basic extends Book {
}
