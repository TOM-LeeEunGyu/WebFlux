package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Funtion<T, R> {
    // T -> R
    open fun apply(t: T?): R?
}
