package com.acuity.prime.exception;

/**
 * Created by Amit on 16/05/2018.
 */
public class PrimeGenerationException extends Exception {

    public PrimeGenerationException() {
        super();
    }

    public PrimeGenerationException(String message) {
        super(message);
    }

    public PrimeGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrimeGenerationException(Throwable cause) {
        super(cause);
    }

    protected PrimeGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
