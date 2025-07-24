package hr.tcom.shoppingcart;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.CartItem;
import hr.tcom.shoppingcart.dto.StatsResponse;
import hr.tcom.shoppingcart.entity.Price;
import hr.tcom.shoppingcart.entity.PriceType;
import hr.tcom.shoppingcart.entity.RecurrenceUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

   @LocalServerPort
   private int port;

   @Autowired
   private TestRestTemplate restTemplate;

   private String baseUrl() {
      return "http://localhost:" + port + "/cart";
   }

   @Test
   public void shouldAddItemToCartAndRetrieveItSuccessfully() {
      String customerId = "customer123";

      // Prepare cart item
      CartItem cartItem = new CartItem();
      cartItem.setOfferId("offer001");
      Price price = new Price();
      price.setPriceType(PriceType.RECURRING);
      price.setRecurrence(2);
      price.setRecurrenceUnit(RecurrenceUnit.DAYS);
      price.setPriceValue(new BigDecimal(10.5));
      cartItem.setPrices(List.of(price));

      // Set headers
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      // Create HTTP request with item and headers
      HttpEntity<CartItem> request = new HttpEntity<>(cartItem, headers);

      // Send POST request to add item to cart
      ResponseEntity<Void> addItemResponse = restTemplate.postForEntity(
            baseUrl() + "/" + customerId + "/item",
            request,
            Void.class
      );

      // Verify item was added successfully
      assertThat(addItemResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

      // Send GET request to retrieve the cart
      ResponseEntity<String> getCartResponse = restTemplate.getForEntity(
            baseUrl() + "/" + customerId,
            String.class
      );

      // Verify cart contents
      assertThat(getCartResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(getCartResponse.getBody()).contains("offer001");
   }

   @Test
   public void testRemoveItemAndEvictCart() {
      String customerId = "customer456";

      // Add item
      CartItem item = new CartItem();
      item.setOfferId("offerToRemove");

      HttpEntity<CartItem> request = new HttpEntity<>(item);
      restTemplate.postForEntity(baseUrl() + "/" + customerId + "/item", request, Void.class);

      // Remove item
      ResponseEntity<Void> removeResponse = restTemplate.exchange(
            baseUrl() + "/" + customerId + "/item/itemToRemove",
            HttpMethod.DELETE, null, Void.class);

      assertThat(removeResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

      // Evict cart
      ResponseEntity<Void> evictResponse = restTemplate.exchange(
            baseUrl() + "/" + customerId,
            HttpMethod.DELETE, null, Void.class);

      assertThat(evictResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
   }

   @Test
   public void testGetStats() {
      String offerId = "stats-offer";
      Action action = Action.ADD; // assuming Action enum has ADD
      LocalDate from = LocalDate.now().minusDays(10);
      LocalDate to = LocalDate.now();

      String uri = UriComponentsBuilder.fromHttpUrl(baseUrl() + "/stats")
            .queryParam("offerId", offerId)
            .queryParam("action", action)
            .queryParam("from", from)
            .queryParam("to", to)
            .toUriString();

      ResponseEntity<StatsResponse> response = restTemplate.getForEntity(uri, StatsResponse.class);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(response.getBody()).isNotNull();
      assertThat(response.getBody().getCount()).isGreaterThanOrEqualTo(0);
   }
}