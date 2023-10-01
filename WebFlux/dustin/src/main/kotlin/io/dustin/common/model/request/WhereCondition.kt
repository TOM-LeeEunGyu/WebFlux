package io.dustin.common.model.request

/**
 * 코드 이해 안됨
 */
data class WhereCondition(
    val column: String,
    val value: Any,
) {
    companion object {
        fun from(key: String, value: Any): WhereCondition {
            return WhereCondition(
                column = key,
                value = value
            )
        }
    }
}