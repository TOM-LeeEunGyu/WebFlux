package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Consumer<T> {
    // T -> void
    open fun accept(t: T?)
}
