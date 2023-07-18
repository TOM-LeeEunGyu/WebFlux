package io.dustin.apps.common.utils

import io.dustin.apps.common.exception.DataNotFoundException

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object OptionalUtils {
    fun <T> getEntity(optional: Optional<T?>?, clazz: Class<T?>?, errorMessage: String?): T? {
        return if (optional.isPresent()) {
            optional.get()
        } else {
            throw DataNotFoundException("데이터가 없습니다.")
        }
    }
}
