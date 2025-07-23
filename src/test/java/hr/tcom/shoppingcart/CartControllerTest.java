package hr.tcom.shoppingcart;

import hr.tcom.shoppingcart.entity.Action;
import hr.tcom.shoppingcart.entity.rdb.CartItem;
import hr.tcom.shoppingcart.dto.StatsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.time.LocalDate;

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
   public void testAddAndGetCart() {
      String customerId = "customer123";

      // Create a cart item
      CartItem item = new CartItem();;
      item.setOfferId("offer001");

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<CartItem> request = new HttpEntity<>(item, headers);

      // Add item
      ResponseEntity<Void> addResponse = restTemplate.postForEntity(
            baseUrl() + "/" + customerId + "/item", request, Void.class);

      assertThat(addResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

      // Get cart
      ResponseEntity<String> getCart = restTemplate.getForEntity(
            baseUrl() + "/" + customerId, String.class);

      assertThat(getCart.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(getCart.getBody()).contains("offer001");
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