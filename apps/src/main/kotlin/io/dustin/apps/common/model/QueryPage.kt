package io.dustin.apps.common.model

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

class QueryPage (

    val page: Int? = null,
    val nextId: Int? = null,
    val column: String? = null,
    val sort: Sort.Direction? = null,
    val size: Int,

) {

    fun offset(): Int {

        // null이 아니라면 -1 빼고 아니면 0
        return page?.minus(1) ?: 0
    }

    fun limit(): Int {
        return size
    }

    fun pageable(): PageRequest {
        var resultSort = Sort.unsorted()
        if (column != null && sort != null) {
            resultSort = Sort.by(sort, column)
        }
        return PageRequest.of(offset(), limit(), resultSort)
    }
}
