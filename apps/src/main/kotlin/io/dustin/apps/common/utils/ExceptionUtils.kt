package io.dustin.apps.common.utils

import io.dustin.apps.common.exception.DataNotFoundException

/**
 * id로 조회된 entity가 없을 경우
 */
fun dataNotFound(): Nothing {
    throw DataNotFoundException()
}

/**
 * 메세지가 있는 경우
 */
fun dataNotFound(message: String?): Nothing {
    if(message == null) {
        dataNotFound()
    } else {
        throw DataNotFoundException(message)
    }
}