package hr.tcom.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication
public class Main {
   public static void main(String[] args) {
      SpringApplication.run(Main.class, args);
   }
}