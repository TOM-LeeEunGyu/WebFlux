package io.medium.poc.common.advice

import io.medium.poc.common.exception.CallProceduresException
import io.medium.poc.common.exception.FeignClientErrorException
import io.medium.poc.common.exception.NotFoundEntityException
import io.medium.poc.common.exception.UniqueValueException
import io.medium.poc.common.model.response.ApiError
import io.medium.poc.common.utils.nowLocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.regex.Pattern

@RestControllerAdvice
class RestApiAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundEntityException::class)
    fun handleNotFoundEntityException(ex: NotFoundEntityException): ApiError {
        return ApiError(
            code = HttpStatus.NOT_FOUND.value(),
            message = ex.message!!,
            timestamp = nowLocalDateTime(),
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleWebExchangeBindException(ex: MethodArgumentNotValidException): ApiError {
        val errors = ex.bindingResult.allErrors.joinToString(separator = " | ") {
            it.defaultMessage!!
        }
        return ApiError(
            code = HttpStatus.BAD_REQUEST.value(),
            message = errors,
            timestamp = nowLocalDateTime(),
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FeignClientErrorException::class)
    fun handleException(ex: FeignClientErrorException): ApiError {
        return ApiError(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = ex.message!!,
            timestamp = nowLocalDateTime(),
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UniqueValueException::class)
    fun handleUniqueValueException(ex: UniqueValueException): ApiError {
        return ApiError(
            code = HttpStatus.BAD_REQUEST.value(),
            message = ex.message!!,
            timestamp = nowLocalDateTime(),
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ApiError {
        val nullParam = Pattern.compile("value for creator parameter ([^\\]]+) which is a non-nullable type")
        val matcher = nullParam.matcher(ex.cause!!.message)
        val message = if (matcher.find()) {
            "요청 JSON key [${matcher.group(1)}]가 누락되었습니다."
        } else {
            ex.message!!
        }
        return ApiError(
            code = HttpStatus.BAD_REQUEST.value(),
            message = message,
            timestamp = nowLocalDateTime(),
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CallProceduresException::class)
    fun handleCallProceduresException(ex: CallProceduresException): ApiError {
        return ApiError(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = ex.message!!,
            timestamp = nowLocalDateTime(),
        )
    }

}
