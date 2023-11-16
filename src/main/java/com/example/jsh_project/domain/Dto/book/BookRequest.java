package com.example.jsh_project.domain.Dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    public BookRequest() {
    }
    private Long id;
    private String title;
    private String content;
    private Integer stock;
    private Integer price;
    private String img;

    private String book_id;
}
