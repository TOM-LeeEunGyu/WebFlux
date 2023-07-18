package io.dustin.apps.common.model

import java.util.List

@JvmRecord
data class CountByPagingInfo<T>(val result: List<T?>?, val isLast: Boolean, val nextId: Long?)
