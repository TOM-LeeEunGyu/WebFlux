package io.dustin.apps.common.advice

import SelectDate
import io.dustin.apps.common.exception.BadRequestParameterException
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime.now
import javax.naming.AuthenticationException


@RestControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFoundException(e: DataNotFoundException): ApiError {
        return ApiError(
            status = HttpStatus.NOT_FOUND.value(),
            message = e.message,
            timestamp = SelectDate.TYPE_FOUR.transform(now())
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestParameterException::class)
    fun handleBadRequestParameterException(e: BadRequestParameterException): ApiError {
        return ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.message,
            timestamp = SelectDate.TYPE_FOUR.transform(now())
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResponseStatusException::class)
    fun handleBadRequestParameterException(e: ResponseStatusException): ApiError {
        return ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.message,
            timestamp = SelectDate.TYPE_FOUR.transform(now())
        )
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(e: ResponseStatusException): ApiError {
        return ApiError(
            status = HttpStatus.BAD_REQUEST.value(),
            message = e.message,
            timestamp = SelectDate.TYPE_FOUR.transform(now())
        )
    }


    data class ApiError(
        val status: Int,
        val message: String?,
        val timestamp: String?
    )
}


