package hr.tcom.shoppingcart.dto;

import lombok.Data;

@Data
public class StatsResponse {
   private long count;

   public StatsResponse(long count) {
      this.count = count;
   }
}
