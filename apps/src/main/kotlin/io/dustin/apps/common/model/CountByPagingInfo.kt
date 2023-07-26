package io.dustin.apps.common.model

data class CountByPagingInfo<T>(
    val result: List<T>,
    val isLast: Boolean,
    val nextId: Long?
)
