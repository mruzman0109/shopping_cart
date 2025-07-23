package hr.tcom.shoppingcart.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO {
   private String customerId;
   private List<CartItemDTO> items = new ArrayList<>();
}
