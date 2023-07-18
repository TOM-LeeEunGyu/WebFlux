package io.dustin.apps.common.utils

import lombok.AccessLevel

/**
 * date utils
 * created by basquiat
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
object DateUtils {
    /**
     * pattern에 맞춰 LocalDateTime -> String 형식으로 변환
     * @param localDateTime
     * @param pattern
     * @return
     */
    fun localDateTimeToStringWithPattern(localDateTime: LocalDateTime?, pattern: String?): String? {
        return try {
            localDateTime.format(DateTimeFormatter.ofPattern(pattern).withLocale(Locale("ko")))
        } catch (e: Exception) {
            null
        }
    }
}
