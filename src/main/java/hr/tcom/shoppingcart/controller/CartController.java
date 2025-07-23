package hr.tcom.shoppingcart.controller;

import hr.tcom.shoppingcart.dto.StatsResponse;
import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.Cart;
import hr.tcom.shoppingcart.entity.CartItem;
import hr.tcom.shoppingcart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/cart")
public class CartController {

   @Autowired
   private CartService cartService;

   @GetMapping("/{customerId}")
   @Operation(summary = "Get current cart for a customer")
   public ResponseEntity<Cart> getCart(@PathVariable String customerId) {
      return ResponseEntity.ok(cartService.getCart(customerId));
   }

   @PostMapping("/{customerId}/item")
   @Operation(summary = "Add item to cart")
   public ResponseEntity<Void> addItem(@PathVariable String customerId, @Valid @RequestBody CartItem item) {
      if (cartService.addItem(customerId, item)) {
         return ResponseEntity.status(HttpStatus.CREATED).build();
      }
      return ResponseEntity.badRequest().build();
   }

   @DeleteMapping("/{customerId}/item/{itemId}")
   @Operation(summary = "Remove item from cart")
   public ResponseEntity<Void> removeItem(@PathVariable String customerId, @PathVariable String itemId) {
      if (cartService.removeItem(customerId, itemId)) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
   }

   @DeleteMapping("/{customerId}")
   @Operation(summary = "Remove customer from cart")
   public ResponseEntity<Void> evictCart(@PathVariable String customerId) {
      if (cartService.evictCart(customerId)) {
         return ResponseEntity.noContent().build();
      }
      return ResponseEntity.notFound().build();
   }

   @GetMapping("/stats")
   @Operation(summary = "Get status of an offer in the cart")
   public ResponseEntity<StatsResponse> getStats(@RequestParam String offerId,
         @RequestParam Action action,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
      return ResponseEntity.ok(new StatsResponse(cartService.getOfferStatistics(offerId, action, from, to)));
   }
}
