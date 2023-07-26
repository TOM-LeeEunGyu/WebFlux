package io.dustin.apps.common.model

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class QueryPage (

    val page: Long? = null,
    val nextId: Long? = null,
    val column: String? = null,
    val sort: Sort.Direction? = null,
    val size: Long,

) {

    fun offset(): Long {

        // null이 아니라면 -1 빼고 아니면 0
        return page?.minus(1) ?: 0
    }

    fun limit(): Long {
        return size
    }

    fun pageable(): PageRequest {
        var resultSort = Sort.unsorted()
        if (column != null && sort != null) {
            resultSort = Sort.by(sort, column)
        }
        return PageRequest.of(offset().toInt(), limit().toInt(), resultSort)
    }
}
