package io.dustin.apps.common.exception

import kotlin.jvm.JvmRecord
import kotlin.Throws

class DuplicatedVoteException(message: String?) : RuntimeException(message)
