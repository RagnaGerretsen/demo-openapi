package demo.use.starters.exception;

public class PaymentNotMadeException extends RuntimeException {

    public PaymentNotMadeException(final String msg) {
        super(msg);
    }

}
