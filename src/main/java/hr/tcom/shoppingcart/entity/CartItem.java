package hr.tcom.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(indexes = {
      @Index(name = "idx_action", columnList = "action, offerId, saleDate")
})
public class CartItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @JsonIgnore
   @Schema(hidden = true)
   private Long id;

   @NotNull
   @Schema(description = "Identifier of the offer this cart item refers to", example = "OFF-12345", required = true)
   private String offerId;

   @Enumerated(EnumType.STRING)
   private Action action;


   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @Valid
   private List<Price> prices = new ArrayList<>();

   @Schema(description = "The sale date associated with this cart item", example = "2025-07-23")
   private LocalDate saleDate;

   @CreationTimestamp
   @Schema(description = "Timestamp when the cart item was persisted", example = "2025-07-23T12:34:56")
   private LocalDateTime timestamp;
}