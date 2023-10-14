package com.example.jsh_project.domain.Dto.request;

import com.example.jsh_project.domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class MemberDto {
    private Long id;
    private String email;
    private String employName;
    private String phoneNumber;
    private String password;
    private String adminKey;

    public static MemberDto fromEntity(Member member)  {
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .employName(member.getEmployName())
                .phoneNumber(member.getPhoneNumber())
                .build();
        return memberDto;
    }
}