package com.example.jsh_project.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Board {
    @Id @GeneratedValue
    private Long id;
    @JoinColumn(name = "board_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BoardConfig board_id;

    private String title;
    private String content;
    private String date;
    private String name;

    @JoinColumn(name = "member")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;
}
