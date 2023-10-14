package com.example.jsh_project.repository;

import com.example.jsh_project.domain.Entity.BoardConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BoardConfigRepository {
    @PersistenceContext
    EntityManager em;

    public void save(BoardConfig boardConfig){
        em.persist(boardConfig);
    }
    public BoardConfig findById(Long id){
        return em.find(BoardConfig.class,id);
    }
}
