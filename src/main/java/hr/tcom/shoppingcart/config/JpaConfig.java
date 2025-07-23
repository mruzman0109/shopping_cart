package hr.tcom.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile({"h2", "postgres"})
@EnableJpaRepositories(basePackages = "hr.tcom.shoppingcart.repository.rdb")
public class JpaConfig {
}
