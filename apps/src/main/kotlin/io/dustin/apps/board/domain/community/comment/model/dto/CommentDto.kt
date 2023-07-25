package io.dustin.apps.board.domain.community.comment.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.IdAble
import java.time.LocalDateTime

data class CommentDto(
    val id: Long,
    val content: String,
    val userId: Long,
    val postingId: Long,
    val replyId: Long?,
    val createdAt: LocalDateTime
) : IdAble {

    var isLike: Boolean = false
    var isReply: Boolean = false

    // 주석 처리 및 중복된 프로퍼티 제거
    companion object {
        fun from(comment: Comment) = with(comment) {
            CommentDto(
                id = id ?: 0L,
                content = content,
                userId = userId,
                postingId = postingId,
                replyId = replyId,
                createdAt = createdAt
            )
        }
    }

    override fun id(): Long = id
}
