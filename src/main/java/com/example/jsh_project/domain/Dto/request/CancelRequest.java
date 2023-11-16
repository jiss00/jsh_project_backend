package com.example.jsh_project.domain.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CancelRequest {
    public CancelRequest() {
    }
    private Long itemId; //item id
    private Integer stock; //살 책의 개수
    private Long member_id;
}
