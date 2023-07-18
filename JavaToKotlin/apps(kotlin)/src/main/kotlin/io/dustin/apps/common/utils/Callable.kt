package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Callable<V> {
    // () -> T
    @Throws(Exception::class)
    open fun call(): V?
}
