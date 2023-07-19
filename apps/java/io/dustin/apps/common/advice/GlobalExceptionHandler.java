package io.dustin.apps.common.advice;

import io.dustin.apps.common.exception.BadRequestParameterException;
import io.dustin.apps.common.exception.DataNotFoundException;
import io.dustin.apps.common.utils.SelectDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    protected ApiError handleDataNotFoundException(DataNotFoundException e) {
        log.error(e.getMessage());
        return ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(SelectDate.TYPE_FOUR.transform(now()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestParameterException.class)
    protected ApiError handleBadRequestParameterException(BadRequestParameterException e) {
        log.error(e.getMessage());
        return ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .timestamp(SelectDate.TYPE_FOUR.transform(now()))
                .build();
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ApiError {
        private int status;
        private String message;
        private String timestamp;
    }

}
