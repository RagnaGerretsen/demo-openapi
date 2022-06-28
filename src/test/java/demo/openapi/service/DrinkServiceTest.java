package demo.openapi.service;

import demo.openapi.domain.CreatedDrink;
import demo.openapi.drink.api.DrinkApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DrinkServiceTest {

    private static final String MOJITO = "mojito";

    @Mock
    private DrinkApiClient drinkApiClient;
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DrinkService drinkService;

    @Test
    void createDrinkTest() {
        final CreatedDrink createdDrink = new CreatedDrink();
        createdDrink.setDrink(MOJITO);

        when(this.drinkApiClient.createDrink(MOJITO)).thenReturn(ResponseEntity.ok(createdDrink));

        // perform test
        final CreatedDrink createdDrinkResult = this.drinkService.createDrink(MOJITO);

        // verify results
        assertThat(createdDrinkResult.getDrink(), equalTo(MOJITO));
    }

    @Test
    void createWrongDrinkTest() {
        ReflectionTestUtils.setField(this.drinkService, "wrongDrinkUri", "dummy-uri/mojito");
        final CreatedDrink createdDrink = new CreatedDrink();
        createdDrink.setDrink(MOJITO);

        when(this.restTemplate.exchange("dummy-uri/mojito", HttpMethod.GET, new HttpEntity<>(HttpHeaders.CONTENT_TYPE), CreatedDrink.class)).thenReturn(ResponseEntity.ok(createdDrink));

        // perform test
        final String createdDrinkResult = this.drinkService.createWrongDrink(MOJITO, true);

        // verify results
        assertThat(createdDrinkResult, equalTo(MOJITO));
    }

}
