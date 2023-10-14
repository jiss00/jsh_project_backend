package com.example.jsh_project.Service;

import com.example.jsh_project.domain.Entity.BoardConfig;
import com.example.jsh_project.repository.BoardConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardConfigService {
    private final BoardConfigRepository boardConfigRepository;
    @Transactional
    public void save(BoardConfig boardConfig){
        boardConfigRepository.save(boardConfig);
    }
    public BoardConfig findById(Long id){
        return boardConfigRepository.findById(id);
    }
}
