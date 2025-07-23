package hr.tcom.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import hr.tcom.shoppingcart.entity.validation.ValidPrice;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@ValidPrice
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Schema(
          description = "Type of the price, e.g., ONE_TIME, RECURRING",
          example = "RECURRING",
          required = true
    )
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)

    private PriceType priceType;

    @Schema(
          description = "The actual value of the price",
          example = "49.99",
          required = true
    )
    @NotNull
    private BigDecimal priceValue;

    // Only for recurring
    @Schema(
          description = "Recurrence interval (e.g., 30). Required if priceType is RECURRING.",
          example = "30",
          nullable = true
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer recurrence; // days/months count
    @Enumerated(EnumType.STRING)
    @Schema(
          description = "Unit of recurrence (e.g., DAYS, MONTHS). Required if priceType is RECURRING.",
          example = "DAYS",
          nullable = true
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RecurrenceUnit recurrenceUnit; // e.g., "days", "months"

}