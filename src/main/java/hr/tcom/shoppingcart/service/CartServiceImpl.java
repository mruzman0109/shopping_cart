package hr.tcom.shoppingcart.service;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.Cart;
import hr.tcom.shoppingcart.entity.CartItem;
import hr.tcom.shoppingcart.repository.CartItemRepository;
import hr.tcom.shoppingcart.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(String customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId).orElseGet(() -> {
            Cart c = new Cart();
            c.setCustomerId(customerId);
            return cartRepository.save(c);
        });
         // Remove from items all items that are marked DELETE in Action
        cart.getItems().removeIf(item -> item.getAction() == Action.DELETE);
        return cart;
    }

    @Override
    public boolean addItem(String customerId, CartItem item) {
        if(item.getOfferId() == null || item.getOfferId().isEmpty()) {
            return false; // Offer ID must not be null or empty
        }
        Cart cart = getCart(customerId);
        cart.getItems().add(item);
        cartRepository.save(cart);
        return true;
    }

    @Override
    public boolean removeItem(String customerId, String itemId) {
        if(customerId == null || customerId.isEmpty() || itemId == null || itemId.isEmpty()) {
            return false; // Customer ID and Item ID must not be null or empty
        }
        Cart cart = getCart(customerId);
        cart.getItems().stream().map(item -> {
            if (item.getOfferId().equals(itemId)) {
                item.setAction(Action.DELETE);
            }
            return item;
        }).forEach(item -> cartRepository.save(cart));
        return true;
    }

    @Override
    @Transactional
    public boolean evictCart(String customerId) {
        if (cartRepository.findByCustomerId(customerId).isPresent()) {
            cartRepository.deleteByCustomerId(customerId);
            return true;
        }
        return false; // Cart for the customer does not exist
    }

    @Override
    public long getOfferStatistics(String offerId, Action action, LocalDate from, LocalDate to) {
        return cartItemRepository.countByOfferIdAndActionAndSaleDateBetween(offerId, action, from, to);
    }
}