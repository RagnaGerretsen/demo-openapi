package demo.openapi.service;

import demo.openapi.domain.CreatedDrink;
import demo.openapi.drink.api.DrinkApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class DrinkServiceTest {

    @Mock
    private DrinkApiClient drinkApiClient;
    @Mock
    private CreatedDrink createdDrink; //DON'T MOCK
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DrinkService drinkService;

    @Test
    void createDrinkTest() {
        final String drink = "mojito";

        given(this.drinkApiClient.createDrink(drink)).willReturn(ResponseEntity.ok(this.createdDrink));

        // perform test
        final CreatedDrink createdDrink = this.drinkService.createDrink(drink);

        // verify results
        assertThat(createdDrink, equalTo(drink));
    }
}
