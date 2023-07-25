package io.dustin.apps.common.model.extension

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.annotations.QueryDelegate
import com.querydsl.core.annotations.QueryEntity
import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.model.QComment

@QueryEntity
object CommentExtension {

    @JvmStatic
    @QueryDelegate(Comment::class)
    fun indexByCountPagination(comment: QComment, nextId: Long?): BooleanBuilder {
        val builder = BooleanBuilder()
        if (nextId != null) {
            builder.and(comment.id.lt(nextId))
        }
        return builder
    }
}
