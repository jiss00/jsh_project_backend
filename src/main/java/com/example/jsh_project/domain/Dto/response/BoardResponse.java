package com.example.jsh_project.domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardResponse {

    private String title;
    private String name;
    private String content;
    private String date;
}
