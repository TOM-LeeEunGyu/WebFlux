package io.dustin.apps.common.utils

import kotlin.jvm.JvmRecord
import kotlin.Throws

@FunctionalInterface
interface Runnable {
    /*
    () -> void
    Runnable을 이용하여 트랜잭션을 분리할 수 있다고 한다.
     */
    fun run()
}
