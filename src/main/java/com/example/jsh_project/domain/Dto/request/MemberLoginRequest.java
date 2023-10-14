package com.example.jsh_project.domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginRequest {
    public MemberLoginRequest() {
    }
    private String name;
    private String email;
    private String password;
}
