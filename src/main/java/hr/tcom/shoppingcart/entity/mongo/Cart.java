package hr.tcom.shoppingcart.entity.mongo;

import hr.tcom.shoppingcart.dto.CartDTO;
import hr.tcom.shoppingcart.dto.CartItemDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cart")
@Data
public class Cart {

   @Id
   private String customerId;

   private List<CartItem> items = new ArrayList<>();

   public void setItemsFromDTO(List<CartItemDTO> itemsFromDTO) {
      if (itemsFromDTO != null) {
         this.items = new ArrayList<>();
         for (CartItemDTO item : itemsFromDTO) {
            CartItem cartItem = new CartItem();
            cartItem.setOfferId(item.getOfferId());;
            cartItem.setAction(item.getAction());
            cartItem.setSaleDate(item.getSaleDate());
            cartItem.setPrices(item.getPrices() != null ? item.getPrices() : new ArrayList<>());
            this.items.add(cartItem);
         }
      }

   }

   public List<CartItemDTO> setItemsToDTO() {
      List<CartItemDTO> itemsDTO = new ArrayList<>();
      for (CartItem item : this.items) {
         CartItemDTO itemDTO = new CartItemDTO();
         itemDTO.setOfferId(item.getOfferId());
         itemDTO.setAction(item.getAction());
         itemDTO.setSaleDate(item.getSaleDate());
         itemDTO.setPrices(item.getPrices() != null ? item.getPrices() : new ArrayList<>());
         itemsDTO.add(itemDTO);
      }
      return itemsDTO;
   }
}
