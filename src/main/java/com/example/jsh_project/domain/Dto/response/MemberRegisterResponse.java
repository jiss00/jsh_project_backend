package com.example.jsh_project.domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberRegisterResponse {
    private String employName;
    private String email;
    private String password;
}
