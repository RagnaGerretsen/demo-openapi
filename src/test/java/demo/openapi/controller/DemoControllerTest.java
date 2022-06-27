package demo.openapi.controller;

import demo.openapi.domain.CreatedDrink;
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

    @Mock
    private CreatedDrink createdDrink;
    @Mock
    private DrinkService drinkService;

    @InjectMocks
    private DemoController controller;

    @Test
    void getDrinkDetails() {
        final CreatedDrink createdDrink = new CreatedDrink();
        createdDrink.setDrink(MOJITO);

        when(this.drinkService.createDrink(MOJITO)).thenReturn(createdDrink);

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getGirlsNightDrink(MOJITO);

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        final CreatedDrink createdDrinkResult = responseEntity.getBody();
        assertThat(createdDrinkResult.getDrink(), equalTo(MOJITO));
    }

    //TODO: create test for wrong drink
}