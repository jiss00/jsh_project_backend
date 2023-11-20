package com.example.jsh_project.domain.Dto.request;

import com.example.jsh_project.domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MemberRegisterRequest {
    private String email;
    private String employName;
    private String phoneNumber;
    private String password;

    public Member toEntity(String password) {
        return Member.builder()
                .email(this.email)
                .employName(this.employName)
                .phoneNumber(this.phoneNumber)
                .password(password)
                .build();
    }
}