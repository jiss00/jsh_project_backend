package com.example.jsh_project.domain.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ViewPurchase {
    public ViewPurchase() {
    }

    private List<String> titles = new ArrayList<>();
    private List<String> imgs = new ArrayList<>();
    private List<Integer> stock = new ArrayList<>();
    private List<Integer> prices = new ArrayList<>();
    private List <Long> id = new ArrayList<>();
    private List <Long> books = new ArrayList<>();
    private List <String> merchant_uid = new ArrayList<>();

    private Integer price;
}
