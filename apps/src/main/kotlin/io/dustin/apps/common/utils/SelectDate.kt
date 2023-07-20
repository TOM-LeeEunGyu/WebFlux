import io.dustin.apps.common.utils.DateUtils
import java.time.LocalDateTime

enum class SelectDate(
    val index: Int,
    val convert: (LocalDateTime) -> String?
) {
    TYPE_ONE(0, { localDateTime ->
        DateUtils.localDateTimeToStringWithPattern(localDateTime, "yyyyMMdd")
    } ),
    TYPE_TWO(1, { localDateTime ->
        DateUtils.localDateTimeToStringWithPattern(localDateTime, "yyyy/MM/dd")
    } ),
    TYPE_THREE(2, { localDateTime -> // 괄호 추가
        DateUtils.localDateTimeToStringWithPattern(localDateTime, "yyyyMMddHHmmss")
    } ),
    TYPE_FOUR(3, { localDateTime ->
        DateUtils.localDateTimeToStringWithPattern(localDateTime, "yyyy-MM-dd hh:mm:ss")
    } );

    fun transform(localDateTime: LocalDateTime): String? {
        return convert(localDateTime)
    }
}
