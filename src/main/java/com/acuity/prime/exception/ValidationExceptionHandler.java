package com.acuity.prime.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Handle Constraints Violation Exception for validating Path Variable.
 * <p>
 * Created by Amit on 16/05/2018.
 */
@ControllerAdvice
@Component
public class ValidationExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(ConstraintViolationException exception) {
        return error(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(toList()));
    }

    private Map error(Object message) {
        return Collections.singletonMap("error", message);
    }
}
