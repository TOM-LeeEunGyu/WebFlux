package io.dustin.common.model.response

data class ApiError(
    val code: Int,
    val message: String,
)