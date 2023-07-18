package io.dustin.apps.common.model

import lombok.Data

@Data
@Slf4j
@ToString
class QueryPage {
    private val page = 0
    private val size = 0
    private val nextId: Long? = null
    private val column: String? = null
    private val sort: Sort.Direction? = null
    fun offset(): Int {
        return page - 1
    }

    fun limit(): Int {
        return size
    }

    fun pageable(): PageRequest? {
        var resultSort: Sort = Sort.unsorted()
        if (column != null && sort != null) {
            resultSort = Sort.by(sort, column)
        }
        return PageRequest.of(offset(), limit(), resultSort)
    }
}
