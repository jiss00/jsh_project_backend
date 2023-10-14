package com.example.jsh_project.domain.Dto.book;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Book_800 {
    public Book_800() {
    }
    private List<String> book_names = Arrays.asList(
            "해커스 토익 실전 1000제 2",
            "YBM 실전 토익 1000제 2",
            "시원스쿨 토익 1500제"
    );
    private List<String> book_img = Arrays.asList(
            "https://contents.kyobobook.co.kr/sih/fit-in/400x0/pdt/S000060310290.jpg",
            "https://image.yes24.com/goods/93756767/XL",
            "https://image.yes24.com/goods/96825933/XL"
    );
    private List<String> id = Arrays.asList(
            "10","11","12"
    );
}
