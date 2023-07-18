package io.dustin.apps.common.model

import io.dustin.apps.common.exception.DataNotFoundException

@NoArgsConstructor(access = AccessLevel.PRIVATE)
object ResponseWithScrollSetting {
    fun <T : IdAble?> getCountByPagingInfo(result: List<T?>?, realSize: Int): CountByPagingInfo<T?>? {
        val querySize = realSize + 1
        val toClient: List<T?>?
        val isLast: Boolean
        val nextId: Long?
        if (result.size() <= realSize) {
            isLast = true
            nextId = null
            toClient = result
        } else {
            isLast = false
            toClient = result.subList(0, realSize)
            nextId = toClient.stream()
                .sorted(Comparator.comparing(T?::getId))
                .findFirst().orElseThrow { DataNotFoundException("데이터에 문제가 있습니다.") }.getId()
        }
        return CountByPagingInfo(toClient, isLast, nextId)
    }
}
