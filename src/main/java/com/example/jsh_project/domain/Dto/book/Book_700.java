package com.example.jsh_project.domain.Dto.book;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Book_700 {
    public Book_700() {
    }
    private List<String> book_names = Arrays.asList(
            "ETS 토익정기시험 기출문제집3","해커스 토익 실전 1000제","토익 단기공략 850+"
    );

    private List<String> book_img = Arrays.asList(
            "https://image.yes24.com/goods/105385355/XL",
            "https://image.yes24.com/goods/116438294/XL",
            "https://image.yes24.com/goods/110329834/XL"
    );
    private List<String> id = Arrays.asList(
            "7","8","9"
    );
}
