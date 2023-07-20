package io.dustin.apps.common.model

import io.dustin.apps.common.exception.DataNotFoundException

object ResponseWithScrollSetting {
    fun <T : IdAble> getCountByPagingInfo(result: List<T>, realSize: Int): CountByPagingInfo<T> {
        val querySize = realSize + 1

        val toClient: List<T>
        val isLast: Boolean
        var nextId: Long? = null
        if (result.size <= realSize) {
            isLast = true
            toClient = result
        } else {
            isLast = false
            toClient = result.subList(0, realSize)
            nextId = toClient.stream()
                .sorted(Comparator.comparingLong { it.getId() })
                .findFirst().orElseThrow { DataNotFoundException("데이터에 문제가 있습니다.") }.getId()
        }
        return CountByPagingInfo(toClient, isLast, nextId)
    }
}
