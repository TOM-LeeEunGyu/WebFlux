package io.dustin.apps.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BadRequestParameterException extends RuntimeException {
    public BadRequestParameterException(String message) {
        super(message);
    }
}
