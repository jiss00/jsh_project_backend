package com.example.jsh_project.controller.Iamport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class Canceldata {
    public Canceldata() {
    }

    private String merchant_id; //주문번호
    private String reason; //환불 이유
    private Long id; // 사용자 id
    private Long purchase_id;
    private Integer stock;
}
