package io.dustin.apps.common.exception

class FeignClientErrorException(message: String? = "데이터 전송중 에러가 발생했습니다.") : RuntimeException(message)