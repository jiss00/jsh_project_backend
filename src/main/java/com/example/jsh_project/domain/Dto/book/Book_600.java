package com.example.jsh_project.domain.Dto.book;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Book_600 {
    public Book_600() {
    }
    private List<String> book_names = Arrays.asList(
        "해커스 토익 700+","해커스 토익 중급","토익 단기공략 750+"
    );
    private List<String> book_img = Arrays.asList(
        "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788965424628.jpg",
            "https://image.yes24.com/goods/64445983/XL","https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788917235944.jpg"
    );
    private List<String> id = Arrays.asList(
            "4","5","6"
    );
}
