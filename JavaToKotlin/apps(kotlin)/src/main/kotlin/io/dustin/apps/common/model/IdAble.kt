package io.dustin.apps.common.model

import kotlin.jvm.JvmRecord
import kotlin.Throws

interface IdAble {
    open fun getId(): Long?
}
