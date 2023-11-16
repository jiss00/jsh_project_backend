package com.example.jsh_project.domain.Dto.request;

import com.example.jsh_project.domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CartRequest {
    public CartRequest() {
    }
    private Long book_id; //책 id
    private Integer stock; //살 책의 개수
    private Long member_id;
    private Integer where; //0은 바로 구매, 1은 장바구니에서 구매

}
