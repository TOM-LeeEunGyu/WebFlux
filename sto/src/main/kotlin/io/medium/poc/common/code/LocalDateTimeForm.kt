package io.medium.poc.common.code

import io.medium.poc.common.utils.localDateTimeToString
import java.time.LocalDateTime

/**
 * string format by date form pattern
 * created by basquiat
 */
enum class LocalDateTimeForm(
    private val format: String,
) {

    YYYY_MM_DD_TIMESTAMP("yyyy-MM-dd HH:mm:ss");

    fun format() = format

    /**
     * enum의 패턴으로 string date form을 반환한다.
     * @param localDateTime
     * @return String
     */
    fun transform(localDateTime: LocalDateTime): String {
        return localDateTimeToString(localDateTime, format)
    }

}
