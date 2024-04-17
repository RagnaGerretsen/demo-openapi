package demo.use.starters.controller;

import demo.use.starters.domain.CreatedDrink;
import demo.use.starters.exception.PaymentNotMadeException;
import demo.use.starters.service.DrinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DemoController implements GirlsNightApi {

    private final DrinkService drinkService;

    @Override
    public ResponseEntity<CreatedDrink> getGirlsNightDrink(final String drink, final Integer payment) {

        if (payment == null || payment == 0) {
            throw new PaymentNotMadeException("No payment provided");
        }

        final CreatedDrink createdDrink = this.drinkService.createDrink(drink);

        return new ResponseEntity<>(createdDrink, HttpStatus.OK);
    }

}