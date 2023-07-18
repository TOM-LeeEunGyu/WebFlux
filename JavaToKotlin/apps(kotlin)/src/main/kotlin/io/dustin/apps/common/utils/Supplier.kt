package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Supplier<T> {
    // () -> T
    open fun get(): T?
}
