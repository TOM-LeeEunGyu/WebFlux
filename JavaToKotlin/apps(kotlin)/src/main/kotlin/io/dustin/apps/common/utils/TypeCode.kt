package io.dustin.apps.common.utils

import lombok.AccessLevel

@AllArgsConstructor(access = AccessLevel.PROTECTED)
enum class TypeCode {
    ONE("A"),
    TWO("B"),
    THREE("C"),
    FOUR("D");

    @Getter
    private val code: String? = null
}
