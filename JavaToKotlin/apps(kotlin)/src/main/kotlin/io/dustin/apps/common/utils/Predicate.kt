package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Predicate<T> {
    // T -> boolean
    open fun test(t: T?): Boolean
}
