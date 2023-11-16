package com.example.jsh_project.Service;

import com.example.jsh_project.domain.Entity.*;
import com.example.jsh_project.repository.BookRepository;
import com.example.jsh_project.repository.MemberRepository;
import com.example.jsh_project.repository.OrderRepository;
import com.example.jsh_project.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    //장바구니 생성
    @Transactional
    public void save(ShoppingBasket basket){
        orderRepository.save(basket);
    }
    @Transactional
    public void saveItem(CartItem item){
        orderRepository.saveItem(item);
    }
    @Transactional
    public ShoppingBasket update(ShoppingBasket basket){
        return orderRepository.update(basket);
    }

    //책들 장바구니에 담기(책의 개수는 안바뀜)
    @Transactional
    public ShoppingBasket cart(Long id,Book book, Integer stock){
        ShoppingBasket basket = findById(id);
        basket.add_price(book.getPrice()*stock);
        basket.addBooks(book);
        CartItem cartItem = new CartItem(basket,book,stock);
        orderRepository.saveItem(cartItem);
        basket.addItems(cartItem);

        bookRepository.update(book);
        return update(basket);
    }

    @Transactional
    public ShoppingList purchase(Long id, Book book,Integer stock,Integer where){
        ShoppingList list = purchaseService.findById(id);
        ShoppingBasket basket = findById(id);
        List<CartItem> cartItems = findItems(id);
        list.add_price(book.getPrice()*stock);
        list.addBooks(book);
        PurchaseItem purchaseItem = new PurchaseItem(list,book,stock);
        purchaseService.saveItem(purchaseItem);
        list.addItems(purchaseItem);
        if(where==1){
            for(int i= 0;i<cartItems.size();i++){
                if(cartItems.get(i).getBook().getId() == book.getId()){
                    CartItem cartItem = cartItems.get(i);
                    basket.removeItems(cartItem);
                    update(basket);
                    break;
                }
            }
        }

        book.removeStock(stock);
        bookRepository.update(book);
        return purchaseRepository.update(list);
    }
    @Transactional//사용자 id(장바구니id + 담은 아이템), 클라에서 취소할 담은 아이템 id 줘야함.
    public void cancel(Long id,Long itemId,Integer quantity){
        ShoppingBasket basket = findById(id);
        CartItem item = orderRepository.findItem(itemId);
        Integer price = item.getBook().getPrice() * quantity;
        if(item.getQuantity()-quantity == 0){
            //구매목록에서 삭제
             basket.removeItems(item);
             item.setQuantity(0);
             deleteItem();
        }
        else{
            //구매목록에서 삭제 x => 수량만 변경
            item.setQuantity(item.getQuantity()-quantity);
        }
        //basket price 감소
        basket.setPrice(basket.getPrice()-price);
        orderRepository.saveItem(item);
        orderRepository.update(basket);
    }



    //사용자 id로 장바구니 찾기
    public ShoppingBasket findById(Long id){
        ShoppingBasket basket = orderRepository.findById(id);
        if(basket == null){
            ShoppingBasket basket1 = new ShoppingBasket();
            basket1.setId(id);
            save(basket1);
            return basket1;
        }
        else{
            return basket;
        }
    }
    //장바구니 책들 모두 얻기
    public List<CartItem> findItems(Long id){
        return orderRepository.findItems(id);
    }

    //장바구니 초기화
    @Transactional
    public void deleteAll(Long id){
        orderRepository.deleteAll(id);
    }
    @Transactional
    public void deleteItem(){
        orderRepository.deleteItem();
    }
}
