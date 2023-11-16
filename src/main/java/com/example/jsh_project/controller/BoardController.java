package com.example.jsh_project.controller;

import com.example.jsh_project.Service.BoardConfigService;
import com.example.jsh_project.Service.BoardService;
import com.example.jsh_project.domain.Dto.request.BoardRequest;
import com.example.jsh_project.domain.Dto.request.DeleteRequest;
import com.example.jsh_project.domain.Dto.request.Search;
import com.example.jsh_project.domain.Dto.response.BoardList;
import com.example.jsh_project.domain.Dto.response.BoardResponse;
import com.example.jsh_project.domain.Entity.Board;
import com.example.jsh_project.domain.Entity.BoardConfig;
import com.example.jsh_project.repository.BoardConfigRepository;
import com.example.jsh_project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:3000" )
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final BoardConfigRepository boardConfigRepository;
    private final BoardConfigService boardConfigService;

    //게시글 작성하기
    @PostMapping("/write")
    public BoardRequest save(@RequestParam String id,@RequestBody BoardRequest boardRequest){
        BoardConfig boardConfig = boardConfigRepository.findById(Long.valueOf(id));
        Board board = new Board();
        if (boardConfig==null){
            BoardConfig boardConfig1 = new BoardConfig();
            boardConfig1.setId(Long.valueOf(id));
            boardConfigService.save(boardConfig1);
            board.setBoard_id(boardConfig1);
        }
        else{
            board.setBoard_id(boardConfig);
        }
        board.setName(boardRequest.getName());
        board.setDate(boardRequest.getDate());
        board.setContent(boardRequest.getContent());
        board.setTitle(boardRequest.getTitle());
        boardService.save(board);
        return boardRequest;
    }

    //게시판 게시글들 불러오기
    @GetMapping("/write")
    public BoardList findAll(@RequestParam String id, @RequestParam String page){
        List<BoardRequest> all = boardService.findAll(id);
        int page1 = Integer.parseInt(page);
        int first_index = (page1 -1) * 5;
        int last_index = Math.min(all.size(),first_index+5);
        BoardList boardList = new BoardList();
        boardList.setBoardlist(all.subList(first_index,last_index));
        boardList.setSize(String.valueOf(all.size()));
        return boardList;
    }

    //해당id 가진 게시글 불러오기
    @GetMapping("/list")
    public BoardResponse view(@RequestParam String id){
        Board board = boardService.findById(Long.valueOf(id));
        BoardResponse boardResponse = new BoardResponse();
        boardResponse.setContent(board.getContent());
        boardResponse.setDate(board.getDate());
        boardResponse.setName(board.getName());
        boardResponse.setTitle(board.getTitle());
        return boardResponse;
    }

    //게시판 검색 기능
    @PostMapping("/search")
    public BoardList search(@RequestBody Search search,@RequestParam String page){
        List<BoardRequest> all = boardService.search(search.getKeyword(), search.getBoard_id());
        int page1 = Integer.parseInt(page);
        int first_index = (page1 -1) * 10;
        int last_index = Math.min(all.size(),first_index+10);
        BoardList boardList = new BoardList();
        boardList.setBoardlist(all.subList(first_index,last_index));
        boardList.setSize(String.valueOf(all.size()));
        return boardList;
    }

    //게시글 삭제 기능
    @PostMapping("/delete")
    public void search(@RequestBody DeleteRequest request){
        boardService.delete(request.getId());
    }

    //사용자가 적은 리뷰들 모두 보기
    @GetMapping("/review")
    public BoardList list(@RequestParam String name, @RequestParam String page){
        List<BoardRequest> all = boardService.findByName(name);
        int page1 = Integer.parseInt(page);
        int first_index = (page1 -1) * 5;
        int last_index = Math.min(all.size(),first_index+5);
        BoardList boardList = new BoardList();
        boardList.setBoardlist(all.subList(first_index,last_index));
        boardList.setSize(String.valueOf(all.size()));
        return boardList;
    }

    //게시글 수정하기
    @PostMapping("/update")
    public void update(@RequestBody BoardRequest boardRequest){
        Board board = boardRepository.findById(boardRequest.getId());
        boardService.update(board,boardRequest.getTitle(),boardRequest.getContent(),boardRequest.getDate());
    }


}
