package hr.tcom.shoppingcart.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "hr.tcom.shoppingcart.repository.mongo")
@EntityScan(basePackages = "hr.tcom.shoppingcart.entity.mongo")
@Profile("mongo")
public class MongoDBConfig {
}
