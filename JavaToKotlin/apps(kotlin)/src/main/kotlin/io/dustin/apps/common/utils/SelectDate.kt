package io.dustin.apps.common.utils

import lombok.Getter

enum class SelectDate(
    /** enum pattern  */
    @field:Getter private val index: Int,
    /** enum code  */
    @field:Getter private val pattern: String?, convert: BiFunction<LocalDateTime?, String?, String?>?
) {
    TYPE_ONE(
        0,
        "yyyyMMdd",
        BiFunction<LocalDateTime?, String?, String?> { localDateTime, pattern ->
            DateUtils.localDateTimeToStringWithPattern(
                localDateTime,
                pattern
            )
        }),
    TYPE_TWO(
        1,
        "yyyy/MM/dd",
        BiFunction<LocalDateTime?, String?, String?> { localDateTime, pattern ->
            DateUtils.localDateTimeToStringWithPattern(
                localDateTime,
                pattern
            )
        }),
    TYPE_THREE(
        2,
        "yyyyMMddHHmmss",
        BiFunction<LocalDateTime?, String?, String?> { localDateTime, pattern ->
            DateUtils.localDateTimeToStringWithPattern(
                localDateTime,
                pattern
            )
        }),
    TYPE_FOUR(
        3,
        "yyyy-MM-dd hh:mm:ss",
        BiFunction<LocalDateTime?, String?, String?> { localDateTime, pattern ->
            DateUtils.localDateTimeToStringWithPattern(
                localDateTime,
                pattern
            )
        });

    /** enum dateConvert  */
    private val convert: BiFunction<LocalDateTime?, String?, String?>?
    fun convertDate(localDateTime: LocalDateTime?, pattern: String?): String? {
        return convert.apply(localDateTime, pattern)
    }

    init {
        this.convert = convert
    }

    fun transform(localDateTime: LocalDateTime?): String? {
        return DateUtils.localDateTimeToStringWithPattern(localDateTime, pattern)
    }
}
