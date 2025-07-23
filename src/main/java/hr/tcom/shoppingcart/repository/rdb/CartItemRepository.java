package hr.tcom.shoppingcart.repository.rdb;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.rdb.CartItem;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository("rdbCartItemRepository")
@Profile({"h2", "postgres"})
public interface CartItemRepository extends JpaRepository<CartItem, String> {
   long countByOfferIdAndActionAndSaleDateBetween(String offerId, Action action, LocalDate from, LocalDate to);
}
