package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Comparator<T> {
    // (T, T) -> int
    open fun compare(o1: T?, o2: T?): Int
}
