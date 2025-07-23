package hr.tcom.shoppingcart.repository.mongo;

import hr.tcom.shoppingcart.entity.mongo.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("mongoCartRepository")
public interface CartRepository extends MongoRepository<Cart, UUID> {
   Optional<Cart> findByCustomerId(String customerId);

   void deleteByCustomerId(String customerId);


}