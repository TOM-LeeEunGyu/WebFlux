package io.medium.poc.common.utils

import io.medium.poc.common.exception.NotFoundEntityException

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