import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.model.CountByPagingInfo
import io.dustin.apps.common.model.IdAble
import java.util.Comparator

object ResponseWithScrollSetting {

    fun <T : IdAble> getCountByPagingInfo(result: List<T>, realSize: Int): CountByPagingInfo<T> {
        val querySize = realSize + 1

        val toClient: List<T>
        val isLast: Boolean
        var nextId: Int? = null

        if (result.size <= realSize) {
            isLast = true
            toClient = result
        } else {
            isLast = false
            toClient = result.subList(0, realSize)
            nextId = toClient.stream()
                .sorted(Comparator.comparing(T::id))
                .findFirst()
                .orElseThrow { DataNotFoundException("데이터에 문제가 있습니다.") }
                .id()


        return CountByPagingInfo(toClient, isLast, nextId)
    }
}
