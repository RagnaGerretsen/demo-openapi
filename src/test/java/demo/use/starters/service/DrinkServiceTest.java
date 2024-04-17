package demo.use.starters.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.library.drink.api.DrinkApiClient;
import demo.use.starters.domain.CreatedDrink;
import demo.use.starters.exception.DemoException;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DrinkServiceTest {

    private static final String MOJITO = "mojito";

    @Mock
    private DrinkApiClient drinkApiClient;
    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private DrinkService drinkService;

    @Test
    void createDrinkTest() {
        final demo.library.domain.CreatedDrink createdDrink = new demo.library.domain.CreatedDrink();
        createdDrink.setDrink(MOJITO);

        final CreatedDrink createdDrinkFinal = new CreatedDrink();
        createdDrinkFinal.setDrink(MOJITO);

        when(this.drinkApiClient.createDrink(MOJITO)).thenReturn(ResponseEntity.ok(createdDrink));
        when(this.objectMapper.convertValue(createdDrink, CreatedDrink.class)).thenReturn(createdDrinkFinal);

        // perform test
        final CreatedDrink createdDrinkResult = this.drinkService.createDrink(MOJITO);

        // verify results
        assertThat(createdDrinkResult.getDrink(), equalTo(MOJITO));
    }

    @Test
    void createDrinkTest_noResponse() {
        when(this.drinkApiClient.createDrink(MOJITO)).thenReturn(ResponseEntity.ok(null));

        // Act and Assert
        assertThrows(DemoException.class, () -> this.drinkService.createDrink(MOJITO));
    }

    @Test
    void createDrinkTest_throwsFeignException() {
        // Arrange
        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());
        FeignException feignException = new FeignException.InternalServerError("", request, null, null);
        doThrow(feignException).when(this.drinkApiClient).createDrink(MOJITO);

        // Act and Assert
        assertThrows(DemoException.class, () -> this.drinkService.createDrink(MOJITO));
    }

}
