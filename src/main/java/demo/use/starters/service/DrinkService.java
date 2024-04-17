package demo.use.starters.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.library.drink.api.DrinkApiClient;
import demo.use.starters.domain.CreatedDrink;
import demo.use.starters.exception.DemoException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DrinkService {

    private static final String ERROR_WHILE_CALLING_DRINK_SERVICE = "Error while calling drink-service:";

    private final DrinkApiClient drinkApiClient;
    private final ObjectMapper objectMapper;

    public CreatedDrink createDrink(final String drink) {
        log.info("Calling drink-service for {}", drink);

        try {
            demo.library.domain.CreatedDrink createdDrinkResponse = this.drinkApiClient.createDrink(drink).getBody();

            if (createdDrinkResponse == null) {
                log.error(ERROR_WHILE_CALLING_DRINK_SERVICE + " response is null");
                throw new DemoException(ERROR_WHILE_CALLING_DRINK_SERVICE + " response is null");
            }

            return this.objectMapper.convertValue(createdDrinkResponse, CreatedDrink.class);

        } catch (FeignException exception) {
            log.error(ERROR_WHILE_CALLING_DRINK_SERVICE + " {}", exception.getMessage());
            throw new DemoException(ERROR_WHILE_CALLING_DRINK_SERVICE, exception);
        }

    }
}
