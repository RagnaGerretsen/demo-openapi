package demo.use.starters.controller.exception;

import demo.use.starters.exception.PaymentNotMadeException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler<T extends Throwable> extends ResponseEntityExceptionHandler {

    private static final int EXCEPTION_MAX_LOG_LENGTH = 2000;

    @ExceptionHandler(PaymentNotMadeException.class)
    protected ResponseEntity<Object> handlePaymentNotMadeException(final PaymentNotMadeException exception) {
        logError(exception.getMessage(), (T) exception);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<Object> handleFeignException(final FeignException exception) {
        logError(exception.getMessage(), (T) exception);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(final String message, final T exception) {
        if (log.isErrorEnabled()) {
            final String stackTrace = StringUtils.left(Arrays.toString(exception.getStackTrace()), EXCEPTION_MAX_LOG_LENGTH);
            try {
                MDC.put("exception", stackTrace);
                log.error(message);
            } finally {
                MDC.clear();
            }

        }
    }

}
