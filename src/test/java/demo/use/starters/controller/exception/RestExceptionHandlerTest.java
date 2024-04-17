package demo.use.starters.controller.exception;

import demo.use.starters.exception.PaymentNotMadeException;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestExceptionHandlerTest {

    private RestExceptionHandler<Throwable> restExceptionHandler;

    @BeforeEach
    public void setUp() {
        restExceptionHandler = new RestExceptionHandler<>();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testHandlePaymentNotMadeException() {
        PaymentNotMadeException exception = new PaymentNotMadeException("Payment not made");
        ResponseEntity<Object> responseEntity = restExceptionHandler.handlePaymentNotMadeException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleFeignException() {
        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());
        FeignException exception = new FeignException.BadRequest("Bad request", request, null, null);
        ResponseEntity<Object> responseEntity = restExceptionHandler.handleFeignException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}