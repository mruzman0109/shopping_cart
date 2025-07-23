package hr.tcom.shoppingcart.repository;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
   long countByOfferIdAndActionAndSaleDateBetween(String offerId, Action action, LocalDate from, LocalDate to);
}
