package demo.use.starters.controller;

import demo.use.starters.domain.CreatedDrink;
import demo.use.starters.domain.DrinkType;
import demo.use.starters.exception.PaymentNotMadeException;
import demo.use.starters.service.DrinkService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        setupCreatedDrinkExpectation(MOJITO, DrinkType.COCKTAIL);

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getGirlsNightDrink(MOJITO, 10);

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        final CreatedDrink createdDrinkResult = responseEntity.getBody();
        assertNotNull(createdDrinkResult);
        assertThat(createdDrinkResult.getDrink(), equalTo(MOJITO));
    }

    @Test
    void getEasyGirlsNightDrink() {
        setupCreatedDrinkExpectation(BEER, DrinkType.BEER);

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getGirlsNightDrink(BEER, 4);

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        final CreatedDrink createdDrinkResult = responseEntity.getBody();
        assertNotNull(createdDrinkResult);
        assertThat(createdDrinkResult.getDrink(), equalTo(BEER));
    }

    @Test
    void getGirlsNightDrink_throwsPaymentNotMadeException() {
        // perform test
        assertThrows(PaymentNotMadeException.class, () -> this.controller.getGirlsNightDrink(BEER, 0));
    }

    private void setupCreatedDrinkExpectation(String beer, DrinkType beer1) {
        final CreatedDrink createdDrink = new CreatedDrink();
        createdDrink.setDrink(beer);
        createdDrink.setDrinkType(beer1);

        when(this.drinkService.createDrink(beer)).thenReturn(createdDrink);
    }
}