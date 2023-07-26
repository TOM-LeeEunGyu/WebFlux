package io.dustin.apps.common.model

data class ResponseWithScroll<T>(
    val result: T,
    val isLast: Boolean,
    val nextId: Long?
) {
    companion object {
        fun <T> from(result: T, isLast: Boolean, nextId: Long?): ResponseWithScroll<T> {
            return ResponseWithScroll(result, isLast, nextId)
        }
    }
}
