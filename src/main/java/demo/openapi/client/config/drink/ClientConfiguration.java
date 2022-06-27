package demo.openapi.client.config.drink;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Note - normally it is enough to just have an empty class
 */
@Configuration("drinkClientConfiguration")
@EnableConfigurationProperties
public class ClientConfiguration {

}
