package demo.use.starters.exception;

public class DemoException extends RuntimeException {

    public DemoException(final String msg) {
        super(msg);
    }

    public DemoException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
