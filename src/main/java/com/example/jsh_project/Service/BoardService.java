package com.example.jsh_project.Service;

import com.example.jsh_project.domain.Dto.request.BoardRequest;
import com.example.jsh_project.domain.Entity.Board;
import com.example.jsh_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board){
        boardRepository.save(board);
    }
    @Transactional
    public void delete(Long id){
        boardRepository.delete(id);
    }
    @Transactional
    public void update(Board board,String title,String content,String date){
       board.setBoard_id(board.getBoard_id());
       board.setId(board.getId());
       board.setName(board.getName());
       board.setTitle(title);
       board.setContent(content);
       board.setDate(date);

    }

    public List<BoardRequest> findAll(String id){
        return boardRepository.findAll(Long.valueOf(id));
    }
    public Board findById(Long id){
        return boardRepository.findById(id);
    }
    public List<BoardRequest> search(String keyword,Long board_id){
        return boardRepository.search(keyword,board_id);
    }


    public List<BoardRequest> findByName(String name) {
         return boardRepository.findByName(name);
    }
}
