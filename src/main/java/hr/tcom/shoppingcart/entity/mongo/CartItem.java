package hr.tcom.shoppingcart.entity.mongo;

import hr.tcom.shoppingcart.dto.CartItemDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart_item")
@Data
public class CartItem extends CartItemDTO {
   @Id
   private String id;
}
