package hr.tcom.shoppingcart.entity.validation;

import hr.tcom.shoppingcart.entity.Price;
import hr.tcom.shoppingcart.entity.PriceType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<ValidPrice, Price> {

   @Override
   public boolean isValid(Price price, ConstraintValidatorContext context) {
      if (price == null || price.getPriceType() == null) return true; // handle null safely

      boolean isRecurring = price.getPriceType() == PriceType.RECURRING;
      boolean hasRecurrenceFields = price.getRecurrence() != null && price.getRecurrenceUnit() != null;

      if (isRecurring && !hasRecurrenceFields) {
         return false; // RECURRING must have both fields
      }

      if (!isRecurring && (price.getRecurrence() != null || price.getRecurrenceUnit() != null)) {
         return false; // ONE_TIME must not have recurrence fields
      }

      return true;
   }
}
