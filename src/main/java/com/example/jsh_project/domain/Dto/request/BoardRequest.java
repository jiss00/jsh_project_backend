package com.example.jsh_project.domain.Dto.request;

import com.example.jsh_project.domain.Entity.BoardConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BoardRequest {
    public BoardRequest() {
    }
    private Long id;
    private String title;
    private String content;
    private String date;
    private String name;

}
