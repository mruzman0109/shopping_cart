package hr.tcom.shoppingcart.service;

import hr.tcom.shoppingcart.dto.CartDTO;
import hr.tcom.shoppingcart.dto.CartItemDTO;
import hr.tcom.shoppingcart.dto.CartMapper;
import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.rdb.Cart;
import hr.tcom.shoppingcart.repository.rdb.CartItemRepository;
import hr.tcom.shoppingcart.repository.rdb.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Profile("h2 | postgres")
public class CartServiceRDBImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDTO getCart(String customerId) {
        Cart cart = cartRepository.findByCustomerId(customerId).orElseGet(() -> {
            Cart c = new Cart();
            c.setCustomerId(customerId);
            return cartRepository.save(c);
        });
         // Remove from items all items that are marked DELETE in Action
        cart.getItems().removeIf(item -> item.getAction() == Action.DELETE);
         // Convert Cart to CartDTO
        return CartMapper.toDTO(cart);
    }

    @Override
    public boolean addItem(String customerId, CartItemDTO item) {
        if(item.getOfferId() == null || item.getOfferId().isEmpty()) {
            return false; // Offer ID must not be null or empty
        }
        CartDTO cart = getCart(customerId);
        cart.getItems().add(item);
        cartRepository.save(CartMapper.toEntity(cart));
        return true;
    }

    @Override
    public boolean removeItem(String customerId, String itemId) {
        if(customerId == null || customerId.isEmpty() || itemId == null || itemId.isEmpty()) {
            return false; // Customer ID and Item ID must not be null or empty
        }
        CartDTO cart = getCart(customerId);
        cart.getItems().stream().map(item -> {
            if (item.getOfferId().equals(itemId)) {
                item.setAction(Action.DELETE);
            }
            return item;
        }).forEach(item -> cartRepository.save(CartMapper.toEntity(cart)));
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