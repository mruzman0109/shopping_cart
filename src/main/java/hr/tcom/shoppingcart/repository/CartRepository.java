package hr.tcom.shoppingcart.repository;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
   Optional<Cart> findByCustomerId(String customerId);

   void deleteByCustomerId(String customerId);


}