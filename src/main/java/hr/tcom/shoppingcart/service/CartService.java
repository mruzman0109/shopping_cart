package hr.tcom.shoppingcart.service;

import hr.tcom.shoppingcart.dto.CartDTO;
import hr.tcom.shoppingcart.dto.CartItemDTO;
import hr.tcom.shoppingcart.entity.Action;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface CartService {
    CartDTO getCart(String customerId);
    boolean addItem(String customerId, CartItemDTO item);
    boolean removeItem(String customerId, String itemId);
    boolean evictCart(String customerId);
    long getOfferStatistics(String offerId, Action action, LocalDate from, LocalDate to);
}