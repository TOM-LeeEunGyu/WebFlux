package io.dustin.apps.common.utils

import io.dustin.apps.common.exception.DataNotFoundException

/**
 * id로 조회된 entity가 없을 경우
 */
fun dateNotFound(): Nothing {
    throw DataNotFoundException()
}

/**
 * 메세지가 있는 경우
 */
fun dateNotFound(message: String?): Nothing {
    if(message == null) {
        dateNotFound()
    } else {
        throw DataNotFoundException(message)
    }
}