package com.example.jsh_project.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Entity
public class BoardConfig {
    @Id
    private Long id;

    @OneToMany(mappedBy = "board_id",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Board> board_id;
}
