package io.medium.poc.common.exception

class UniqueValueException(message: String? = "유니크한 값으로 이미 존재하는 값입니다.") : RuntimeException(message)