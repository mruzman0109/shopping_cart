package hr.tcom.shoppingcart.service;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.Cart;
import hr.tcom.shoppingcart.entity.CartItem;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface CartService {
    Cart getCart(String customerId);
    boolean addItem(String customerId, CartItem item);
    boolean removeItem(String customerId, String itemId);
    boolean evictCart(String customerId);
    long getOfferStatistics(String offerId, Action action, LocalDate from, LocalDate to);
}