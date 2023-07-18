package io.dustin.apps.common.model.extension

import com.querydsl.core.BooleanBuilder

object NoticeExtension {
    @QueryDelegate(Notice::class)
    fun indexByCountPagination(notice: QNotice?, nextId: Long?): BooleanBuilder? {
        val builder = BooleanBuilder()
        if (nextId != null) {
            builder.and(notice.id.lt(nextId))
        }
        return builder
    }
}
