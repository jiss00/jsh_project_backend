package com.example.jsh_project.domain.Dto.response;

import com.example.jsh_project.domain.Dto.request.BoardRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardList {
    private List<BoardRequest> boardlist;
    private String size;
}
