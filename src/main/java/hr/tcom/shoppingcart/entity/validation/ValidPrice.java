package hr.tcom.shoppingcart.entity.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceValidator.class)
@Documented
public @interface ValidPrice {
   String message() default "Invalid recurrence fields for the selected price type";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
