package top.guke.helloservice.exception;

public class TimePeriodMismatchException extends RuntimeException {
    public TimePeriodMismatchException(String message) {
        super(message);
    }
}