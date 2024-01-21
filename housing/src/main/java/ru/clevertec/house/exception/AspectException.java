package ru.clevertec.house.exception;

public class AspectException extends RuntimeException {

    public AspectException() {
        super();
    }

    public AspectException(String message, Throwable cause) {
        super(message, cause);
    }

    public AspectException(String message) {
        super(message);
    }
}