package hr.tcom.shoppingcart.repository.rdb;

import hr.tcom.shoppingcart.entity.rdb.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("rdbCartRepository")
public interface CartRepository extends JpaRepository<Cart, UUID> {
   Optional<Cart> findByCustomerId(String customerId);

   void deleteByCustomerId(String customerId);


}