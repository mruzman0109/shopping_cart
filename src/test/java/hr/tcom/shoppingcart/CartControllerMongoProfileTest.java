package hr.tcom.shoppingcart;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("mongo")
public class CartControllerMongoProfileTest extends CartControllerTest {
   // no need to override or add anything, inherits all tests and runs with mongo profile
}
