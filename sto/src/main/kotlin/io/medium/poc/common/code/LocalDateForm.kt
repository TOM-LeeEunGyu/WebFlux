package io.medium.poc.common.code

import io.medium.poc.common.utils.localDateToString
import java.time.LocalDate

/**
 * string format by date form pattern
 * created by basquiat
 */
enum class LocalDateForm(
    private val format: String,
) {

    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DD_WITH_DOT("yyyy.MM.dd"),
    YYYY_MM_DD_NO_DELIMITER("yyyyMMdd");

    fun format() = format

    /**
     * enum의 패턴으로 string date form을 반환한다.
     * @param localDate
     * @return String
     */
    fun transform(localDate: LocalDate): String {
        return localDateToString(localDate, format)
    }

}
