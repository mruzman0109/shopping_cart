package hr.tcom.shoppingcart.repository.mongo;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.mongo.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository("mongoCartItemRepository")
public interface CartItemRepository extends MongoRepository<CartItem, String> {
   long countByOfferIdAndActionAndSaleDateBetween(String offerId, Action action, LocalDate from, LocalDate to);
}
