package com.example.jsh_project.domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RecommendResponse {
    String[] booknames;
    String[] bookimgs;
    String[] bookid;
}
