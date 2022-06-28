package demo.openapi.controller;

import demo.openapi.domain.CreatedDrink;
import demo.openapi.domain.DrinkType;
import demo.openapi.service.DrinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DemoControllerTest {

    private static final String MOJITO = "mojito";
    private static final String BEER = "beer";

    @Mock
    private DrinkService drinkService;

    @InjectMocks
    private DemoController controller;

    @Test
    void getGirlsNightDrink() {
        final CreatedDrink createdDrink = new CreatedDrink();
        createdDrink.setDrink(MOJITO);
        createdDrink.setDrinkType(DrinkType.COCKTAIL);

        when(this.drinkService.createDrink(MOJITO)).thenReturn(createdDrink);

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getGirlsNightDrink(MOJITO);

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        final CreatedDrink createdDrinkResult = responseEntity.getBody();
        assertThat(createdDrinkResult.getDrink(), equalTo(MOJITO));
    }

    @Test
    void getEasyGirlsNightDrink() {
        final CreatedDrink createdDrink = new CreatedDrink();
        createdDrink.setDrink(BEER);
        createdDrink.setDrinkType(DrinkType.BEER);

        when(this.drinkService.createDrink(BEER)).thenReturn(createdDrink);

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getGirlsNightDrink(BEER);

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        final CreatedDrink createdDrinkResult = responseEntity.getBody();
        assertThat(createdDrinkResult.getDrink(), equalTo(BEER));
    }

    @Test
    void getBadGirlsNightDrink() {
        when(this.drinkService.createWrongDrink(MOJITO, true)).thenReturn(BEER);

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getBadGirlsNightDrink(MOJITO, true);

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        // createWrongDrink creates the drink from the previous request that was held in memory
        final CreatedDrink createdDrinkResult = responseEntity.getBody();
        assertThat(createdDrinkResult.getDrink(), equalTo(BEER));
    }
}