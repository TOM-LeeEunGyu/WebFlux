package io.dustin.apps.common.exception

class NotFoundEntityException(message: String? = "조회된 엔티티 정보가 없습니다.") : RuntimeException(message)