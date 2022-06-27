package demo.openapi.controller;

import demo.openapi.domain.CreatedDrink;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
class DemoControllerTest {

    @InjectMocks
    private DemoController controller;

    @Test
    void getDrinkDetails() {

        // perform test
        ResponseEntity<CreatedDrink> responseEntity = this.controller.getGirlsNightDrink("mojito");

        // verify results
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));

        final CreatedDrink createdDrink = responseEntity.getBody();
        assertThat(createdDrink.getDrink(), equalTo("mojito"));
    }
}