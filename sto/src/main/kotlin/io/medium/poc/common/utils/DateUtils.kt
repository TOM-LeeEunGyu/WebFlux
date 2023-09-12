package io.medium.poc.common.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * local date time to string
 * created by basquiat
 *
 * @param localDateTime
 * @param pattern
 * @return String
 */
fun localDateTimeToString(localDateTime: LocalDateTime, pattern: String): String {
    return localDateTime.format(DateTimeFormatter.ofPattern(pattern))
}

fun stringToLocalDateTime(str: String, pattern: String): LocalDateTime {
    return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern))
}

fun localDateToString(localDate: LocalDate, pattern: String): String {
    return localDate.format(DateTimeFormatter.ofPattern(pattern))
}

fun stringToLocalDate(str: String, pattern: String): LocalDate {
    return LocalDate.parse(str, DateTimeFormatter.ofPattern(pattern))
}

fun betweenLocalDate(start: LocalDate, end: LocalDate): Boolean {
    val now = LocalDate.now()
    return !now.isAfter(end) && !now.isBefore(start)
}

fun nowLocalDateTime(): LocalDateTime {
    val seoulZoneId = ZoneId.of("Asia/Seoul")
    return LocalDateTime.now(seoulZoneId)
}

fun nowLocalDate(): LocalDate {
    val seoulZoneId = ZoneId.of("Asia/Seoul")
    return LocalDate.now(seoulZoneId)
}