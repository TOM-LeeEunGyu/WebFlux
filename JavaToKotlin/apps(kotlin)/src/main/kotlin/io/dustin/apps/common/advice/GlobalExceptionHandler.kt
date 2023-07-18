package io.dustin.apps.common.advice

import io.dustin.apps.common.exception.BadRequestParameterException

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException::class)
    protected fun handleDataNotFoundException(e: DataNotFoundException?): ApiError? {
        log.error(e.getMessage())
        return builder()
            .status(HttpStatus.NOT_FOUND.value())
            .message(e.getMessage())
            .timestamp(SelectDate.TYPE_FOUR.transform(now()))
            .build()
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestParameterException::class)
    protected fun handleBadRequestParameterException(e: BadRequestParameterException?): ApiError? {
        log.error(e.getMessage())
        return builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(e.getMessage())
            .timestamp(SelectDate.TYPE_FOUR.transform(now()))
            .build()
    }

    @Builder
    @Getter
    @AllArgsConstructor
    class ApiError {
        private val status = 0
        private val message: String? = null
        private val timestamp: String? = null
    }
}
