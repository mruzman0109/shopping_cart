package hr.tcom.shoppingcart.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Cart {

    @Id
    private String customerId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
}