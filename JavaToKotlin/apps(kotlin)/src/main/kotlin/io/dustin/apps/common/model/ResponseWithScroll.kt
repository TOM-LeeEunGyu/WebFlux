package io.dustin.apps.common.model

import lombok.AllArgsConstructor

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class ResponseWithScroll<T> {
    private val result: T? = null
    private val isLast = false
    private val nextId: Long? = null

    companion object {
        fun <T> from(result: T?, isLast: Boolean, nextId: Long?): ResponseWithScroll<T?>? {
            return ResponseWithScroll<Any?>(result, isLast, nextId)
        }
    }
}
