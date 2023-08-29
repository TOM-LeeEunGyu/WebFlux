package io.dustin.apps.common.utils

import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.exception.DuplicatedException
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

/**
 * 데이터가 없을 때(메세지 없음)
 */
fun dataNotFound(): Nothing {
    throw DataNotFoundException()
}

/**
 * 데이터가 없을 때(메세지 있음)
 */
fun dataNotFound(message: String): Nothing {
    if (message == null) {
        dataNotFound()
    } else {
        throw DataNotFoundException(message)
    }
}

/**
 * 이미 존재하는 데이터(메세지 없음)
 */
fun duplicate(): Nothing {
    throw DuplicatedException()
}


/**
 * 이미 존재하는 데이터(메세지 있음)
 */
fun duplicate(message: String): Nothing {
    if (message == null) {
        duplicate()
    } else {
        throw DuplicatedException(message)
    }
}