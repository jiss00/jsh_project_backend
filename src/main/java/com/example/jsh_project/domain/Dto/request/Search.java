package com.example.jsh_project.domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Search {
    private String keyword;

    private Long board_id;
    public Search() {
    }
}
