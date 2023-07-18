package io.dustin.apps.common.model.extension

import com.querydsl.core.BooleanBuilder

@QueryEntity
object CommentExtension {
    @QueryDelegate(Comment::class)
    fun indexByCountPagination(comment: QComment?, nextId: Long?): BooleanBuilder? {
        val builder = BooleanBuilder()
        if (nextId != null) {
            builder.and(comment.id.lt(nextId))
        }
        return builder
    }
}
