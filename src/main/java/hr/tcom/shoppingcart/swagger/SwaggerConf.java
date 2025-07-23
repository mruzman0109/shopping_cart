package hr.tcom.shoppingcart.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConf {

   @Bean
   public OpenAPI shoppingCartOpenAPI() {
      return new OpenAPI()
            .info(new Info().title("Shopping Cart API")
                  .description("API documentation for the Shopping Cart microservice")
                  .version("1.0"));
   }
}
