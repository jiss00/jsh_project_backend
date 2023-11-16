package com.example.jsh_project.Service;

import com.example.jsh_project.domain.Entity.Book;
import com.example.jsh_project.domain.Entity.PurchaseItem;
import com.example.jsh_project.domain.Entity.ShoppingBasket;
import com.example.jsh_project.domain.Entity.ShoppingList;
import com.example.jsh_project.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public void save(ShoppingList list){
        purchaseRepository.save(list);
    }
    @Transactional
    public void saveItem(PurchaseItem item){
        purchaseRepository.saveItem(item);
    }
    @Transactional
    public void delete(Long id){
        purchaseRepository.delete(id);
    }

    @Transactional
    public void update(ShoppingList list){
        purchaseRepository.update(list);
    }
    @Transactional
    public void cancel(Long id, Long purchase_id,Integer stock){
        ShoppingList list = findById(id);
        PurchaseItem item = findById_Item(purchase_id);
        if(item.remove_stock(stock)==0){
            list.getItems().remove(item);
            delete(purchase_id);
        }

        item.getBook().addStock(stock);
        list.remove_price(item.getBook().getPrice() * stock);
        purchaseRepository.update(list);
    }
    //사용자 구매내역 찾기
    public ShoppingList findById(Long id){
        ShoppingList list = purchaseRepository.findById(id);
        if(list == null){
            ShoppingList list1 = new ShoppingList();
            list1.setId(id);
            save(list1);
            return list1;
        }
        else{
            return list;
        }
    }
    public PurchaseItem findById_Item(Long id){
        return purchaseRepository.findItem(id);
    }
    //사용자가 구매한 물건들 찾기
    public List<PurchaseItem> findItems(Long id){
        return purchaseRepository.findItems(id);
    }
}
