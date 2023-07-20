package io.dustin.apps.common.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object DateUtils {

    fun localDateTimeToStringWithPattern(localDateTime: LocalDateTime, pattern: String): String? {
        return try {
            localDateTime.format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale("ko")))
        } catch (e: Exception) {
            null
        }
    }
}
