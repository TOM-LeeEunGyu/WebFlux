package io.dustin.apps.common.utils

import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.exception.NotFoundEntityException

/**
 * 메세지가 없는 경우
 */
fun notFoundEntity(): Nothing {
    throw NotFoundEntityException()
}

/**
 * 메세지가 있는 경우
 *
 * @param message
 */
fun notFoundEntity(message: String?): Nothing {
    if(message == null) {
        notFoundEntity()
    } else {
        throw NotFoundEntityException(message)
    }
}