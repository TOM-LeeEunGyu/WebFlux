package io.dustin.apps.common.exception

class DuplicatedException(message: String? = "이미 존재하는 데이터 입니다.") : RuntimeException(message)
