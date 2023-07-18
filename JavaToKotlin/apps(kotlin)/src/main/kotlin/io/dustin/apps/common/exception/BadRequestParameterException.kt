package io.dustin.apps.common.exception

import org.springframework.http.HttpStatus

class BadRequestParameterException(message: String?) : RuntimeException(message)
