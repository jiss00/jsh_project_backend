package com.example.jsh_project.repository;

import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.PurchaseItem;
import com.example.jsh_project.domain.Entity.ShoppingBasket;
import com.example.jsh_project.domain.Entity.ShoppingList;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor

public class PurchaseRepository {
    @PersistenceContext
    EntityManager em;

    public void save(ShoppingList list) {
        em.persist(list);
    }
    public void delete(Long id){
        Query query = em.createQuery("delete from PurchaseItem p where p.id = :id")
                .setParameter("id", id);
        query.executeUpdate();
    }


    //id로 장바구니 찾기
    public ShoppingList findById(Long id) {
        return em.find(ShoppingList.class,id);
    }

    public ShoppingList update(ShoppingList list) {
        return em.merge(list);
    }

    public void cancel(Long id, Book book) {

    }

    //구매한 아이템,수량 저장
    public void saveItem(PurchaseItem item) {
        em.persist(item);
    }
    public List<PurchaseItem> findItems(Long id){
        return em.createQuery("select i from PurchaseItem i where i.shoppingList.id =: id", PurchaseItem.class)
                .setParameter("id", id).getResultList();

    }

    public PurchaseItem findItem(Long id) {
        return em.find(PurchaseItem.class,id);
    }
}
