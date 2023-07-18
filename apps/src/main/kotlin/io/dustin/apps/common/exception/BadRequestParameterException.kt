package io.dustin.apps.common.exception

class BadRequestParameterException(message: String? = "잘못된 요청입니다.") : RuntimeException(message)
