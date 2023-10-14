package com.example.jsh_project.domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Member_recommend {
    public Member_recommend() {
    }
    private String score;
}
