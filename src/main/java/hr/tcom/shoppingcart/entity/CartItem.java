package hr.tcom.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.tcom.shoppingcart.entity.validation.ValidPrice;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

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
    private Long id;

    @NotNull
    private String offerId;

    @Enumerated(EnumType.STRING)
    private Action action;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<Price> prices = new ArrayList<>();

    private LocalDate saleDate;

    //odkad je u bazi
    @CreationTimestamp
    private LocalDateTime timestamp;
}