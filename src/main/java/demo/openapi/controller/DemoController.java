package demo.openapi.controller;

import demo.openapi.domain.CreatedDrink;
import demo.openapi.domain.DrinkType;
import demo.openapi.service.DrinkService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = {"drink"})
@RequiredArgsConstructor
public class DemoController implements GirlsNightApi {

    private final DrinkService drinkService;

    @Override
    public ResponseEntity<CreatedDrink> getGirlsNightDrink(final String drink) {

        final CreatedDrink createdDrink = this.drinkService.createDrink(drink);

        if (DrinkType.COCKTAIL.equals(createdDrink.getDrinkType())) {
            log.info("Complicated drink has been created: {}", createdDrink.getDrink());
        } else {
            log.info("Drink has been poured: {}", createdDrink.getDrinkType());
        }

        return new ResponseEntity<>(createdDrink, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CreatedDrink> getBadGirlsNightDrink(final String drink, final Boolean complicated) {
        final String wrongDrink = this.drinkService.createWrongDrink(drink, complicated);

        CreatedDrink drinkDetails = new CreatedDrink();
        drinkDetails.setDrink(wrongDrink);

        return new ResponseEntity<>(drinkDetails, HttpStatus.OK);
    }

}