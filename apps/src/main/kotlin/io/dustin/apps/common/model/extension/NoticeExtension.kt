package io.dustin.apps.common.model.extension

//import com.querydsl.core.BooleanBuilder
//import com.querydsl.core.annotations.QueryDelegate
//import io.dustin.apps.admin.domain.notice.model.Notice
//
//object NoticeExtension {
//    @QueryDelegate(Notice::class)
//    fun indexByCountPagination(notice: QNotice, nextId: Long?): BooleanBuilder {
//        val builder = BooleanBuilder()
//        if (nextId != null) {
//            builder.and(notice.id.lt(nextId))
//        }
//        return builder
//    }
//}
