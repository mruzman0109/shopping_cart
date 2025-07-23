package hr.tcom.shoppingcart.dto;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.Price;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CartItemDTO {
   @NotNull
   @Schema(description = "Identifier of the offer this cart item refers to", example = "OFF-12345", required = true)
   private String offerId;

   private Action action;

   @Valid
   private List<Price> prices = new ArrayList<>();

   @Schema(description = "The sale date associated with this cart item", example = "2025-07-23")
   private LocalDate saleDate;

   @Schema(description = "Timestamp when the cart item was persisted", example = "2025-07-23T12:34:56")
   private LocalDateTime timestamp;

}