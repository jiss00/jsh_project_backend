package com.example.jsh_project.repository;

import com.example.jsh_project.domain.Dto.request.BoardRequest;
import com.example.jsh_project.domain.Entity.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }
    public void delete(Long id){
        Query query = em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    public List<BoardRequest> findAll(Long id) {
        List<Board> boards = em.createQuery("SELECT b FROM Board b WHERE b.board_id.id = :id", Board.class)
                .setParameter("id", id)
                .getResultList();
        List<BoardRequest> boardResponse = new ArrayList<>();
        extracted(boards, boardResponse);
        return boardResponse;
    }

    private static void extracted(List<Board> boards, List<BoardRequest> boardResponse) {
        for (Board board : boards) {
            BoardRequest response = new BoardRequest();
            response.setId(board.getId());
            response.setContent(board.getContent());
            response.setTitle(board.getTitle());
            response.setName(board.getName());
            response.setDate(board.getDate());
            boardResponse.add(response);
        }
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    public List<BoardRequest> search(String keyword, Long board_id) {
        List<Board> boards = em.createQuery("SELECT b FROM Board b WHERE (b.name LIKE CONCAT('%', :keyword, '%') OR b.title LIKE CONCAT('%', :keyword, '%') OR b.content LIKE CONCAT('%', :keyword, '%')) " +
                        "AND b.board_id.id =:board_id", Board.class)
                .setParameter("keyword", keyword)
                .setParameter("board_id", board_id)
                .getResultList();

        List<BoardRequest> response = new ArrayList<>();
        extracted(boards, response);
        return response;
    }


    public List<BoardRequest> findByName(String name) {
        List<Board> boardList = em.createQuery("SELECT b FROM Board b WHERE b.name = :name", Board.class)
                .setParameter("name", name).getResultList();
        List<BoardRequest> boardResponses = new ArrayList<>();
        extracted(boardList,boardResponses);
        return boardResponses;
    }
}
