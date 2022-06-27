package demo.openapi.service;

import demo.openapi.domain.CreatedDrink;
import demo.openapi.drink.api.DrinkApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkApiClient drinkApiClient;
    private final RestTemplate restTemplate;

    @Value("${wrongDrink.uri:}")
    private String wrongDrinkUri;
    private static final String DRINK = "\\{drink}";

    public String createWrongDrink(final String drink, final boolean isDrinkComplicated) {
        log.info("Calling wrong-drink-service for {}", drink);

        final HttpEntity<String> entity = new HttpEntity<>(HttpHeaders.CONTENT_TYPE);
        final String uri = isDrinkComplicated ? wrongDrinkUri = wrongDrinkUri.replaceFirst(DRINK, drink) : null;

        assert uri != null;
        return this.restTemplate.exchange(uri, HttpMethod.GET, entity, CreatedDrink.class).getBody().toString();
    }

    public CreatedDrink createDrink(final String drink) {
        log.info("Calling drink-service for {}", drink);

        return this.drinkApiClient.createDrink(drink).getBody();
    }
}
