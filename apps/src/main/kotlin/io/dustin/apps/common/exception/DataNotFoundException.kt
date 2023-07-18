package io.dustin.apps.common.exception

class DataNotFoundException(message: String? = "조회된 정보가 없습니다."): RuntimeException(message)