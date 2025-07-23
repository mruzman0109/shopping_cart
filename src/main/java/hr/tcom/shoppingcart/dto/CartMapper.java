package hr.tcom.shoppingcart.dto;

import hr.tcom.shoppingcart.entity.mongo.Cart;
import hr.tcom.shoppingcart.entity.rdb.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

   // === CartDTO <-> hr.tcom.shoppingcart.entity.mongo.Cart (both share CartItemDTO for items) ===

   public static hr.tcom.shoppingcart.entity.mongo.Cart toMongo(CartDTO dto) {
      if (dto == null) return null;
      hr.tcom.shoppingcart.entity.mongo.Cart cart = new hr.tcom.shoppingcart.entity.mongo.Cart();
      cart.setCustomerId(dto.getCustomerId());
      cart.setItemsFromDTO(dto.getItems() != null ? dto.getItems() : new ArrayList<>());
      return cart;
   }

   public static CartDTO toDTO(hr.tcom.shoppingcart.entity.mongo.Cart mongo) {
      if (mongo == null) return null;
      CartDTO dto = new CartDTO();
      dto.setCustomerId(mongo.getCustomerId());
      dto.setItems(mongo.getItems() != null ? mongo.setItemsToDTO() : new ArrayList<>());
      return dto;
   }

   // === CartDTO <-> hr.tcom.shoppingcart.entity.RDB.Cart (needs mapping between CartItemDTO and CartItem) ===

   public static hr.tcom.shoppingcart.entity.rdb.Cart toEntity(CartDTO dto) {
      if (dto == null) return null;
      hr.tcom.shoppingcart.entity.rdb.Cart cart = new hr.tcom.shoppingcart.entity.rdb.Cart();
      cart.setCustomerId(dto.getCustomerId());
      if (dto.getItems() != null) {
         List<CartItem> items = dto.getItems().stream()
               .map(CartMapper::toH2CartItem)
               .collect(Collectors.toList());
         cart.setItems(items);
      } else {
         cart.setItems(new ArrayList<>());
      }
      return cart;
   }

   public static CartDTO toDTO(hr.tcom.shoppingcart.entity.rdb.Cart h2) {
      if (h2 == null) return null;
      CartDTO dto = new CartDTO();
      dto.setCustomerId(h2.getCustomerId());
      if (h2.getItems() != null) {
         List<CartItemDTO> items = h2.getItems().stream()
               .map(CartMapper::toCartItemDTO)
               .collect(Collectors.toList());
         dto.setItems(items);
      } else {
         dto.setItems(new ArrayList<>());
      }
      return dto;
   }

   // === CartItemDTO <-> CartItem ===

   public static CartItem toH2CartItem(CartItemDTO dto) {
      if (dto == null) return null;
      CartItem entity = new CartItem();
      entity.setOfferId(dto.getOfferId());
      entity.setAction(dto.getAction());
      entity.setPrices(dto.getPrices() != null ? dto.getPrices() : new ArrayList<>());
      entity.setSaleDate(dto.getSaleDate());
      entity.setTimestamp(dto.getTimestamp());
      return entity;
   }

   public static CartItemDTO toCartItemDTO(CartItem entity) {
      if (entity == null) return null;
      CartItemDTO dto = new CartItemDTO();
      dto.setOfferId(entity.getOfferId());
      dto.setAction(entity.getAction());
      dto.setPrices(entity.getPrices() != null ? entity.getPrices() : new ArrayList<>());
      dto.setSaleDate(entity.getSaleDate());
      dto.setTimestamp(entity.getTimestamp());
      return dto;
   }
}
