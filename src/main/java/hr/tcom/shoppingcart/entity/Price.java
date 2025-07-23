package hr.tcom.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import hr.tcom.shoppingcart.entity.validation.ValidPrice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@ValidPrice
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriceType priceType;

    @NotNull
    private BigDecimal priceValue;

    // Only for recurring
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer recurrence; // days/months count
    @Enumerated(EnumType.STRING)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RecurrenceUnit recurrenceUnit; // e.g., "days", "months"

}