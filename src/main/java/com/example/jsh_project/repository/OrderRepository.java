package com.example.jsh_project.repository;

import com.example.jsh_project.domain.Entity.CartItem;
import com.example.jsh_project.domain.Entity.PurchaseItem;
import com.example.jsh_project.domain.Entity.ShoppingBasket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    @PersistenceContext
    EntityManager em;


    public void save(ShoppingBasket basket) {
        em.persist(basket);
    }

    //id로 장바구니 찾기
    public ShoppingBasket findById(Long id) {
        ShoppingBasket basket = em.find(ShoppingBasket.class, id);
        return basket;

    }

    public ShoppingBasket update(ShoppingBasket basket) {
        return em.merge(basket);
    }

    public void saveItem(CartItem item) {
        em.merge(item);
    }

    public List<CartItem> findItems(Long id){
        return em.createQuery("select i from CartItem i where i.shoppingBasket.id =: id", CartItem.class)
                .setParameter("id", id).getResultList();

    }

    public CartItem findItem(Long itemId) {
        return em.find(CartItem.class,itemId);
    }

    public CartItem ItemUpdate(CartItem item) {
        return em.merge(item);
    }

    public void deleteAll(Long id) {
        Query query = em.createQuery("delete from CartItem c where c.shoppingBasket.id = :id")
                .setParameter("id", id);
        query.executeUpdate();    }
    public void deleteItem(){
        Query query = em.createQuery("delete from CartItem c where c.quantity=0");
        query.executeUpdate();
    }
    public void deletePurchase(){
        Query query = em.createQuery("delete from PurchaseItem c where c.shoppingList.id= 1");
        query.executeUpdate();
    }

}
