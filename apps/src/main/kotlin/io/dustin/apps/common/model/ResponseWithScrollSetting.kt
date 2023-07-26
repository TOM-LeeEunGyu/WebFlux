import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.IdAble

object ResponseWithScrollSetting {

    fun <T : IdAble> getCountByPagingInfo(result: List<T>, realSize: Long): CountByPagingInfo<T> {
        val querySize = realSize + 1

        val toClient: List<T>
        val isLast: Boolean
        val nextId: Long?

        if (result.size <= realSize) {
            isLast = true
            toClient = result
            nextId = 1
        } else {
            isLast = false
            toClient = result.subList(0, realSize.toInt())
            nextId = toClient.stream()
                .sorted()
                .findFirst()
                .orElseThrow { DataNotFoundException("데이터에 문제가 있습니다.") }
                .id()

        }
        return CountByPagingInfo(toClient, isLast, nextId)
    }}

