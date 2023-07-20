package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.IdAble
import java.time.LocalDateTime

data class PostingListDto(
    val id: Long,
    val userId: Long,
    val subject: String,
    val content: String,
    val createdAt: LocalDateTime
) : IdAble {

    var isLike: Boolean = false
    var isBookmark: Boolean = false
    var commentCnt: Long = 0
    var clickCnt: Long = 0
    var likeCount: Long = 0
    companion object {
        fun from(posting: Posting) = with(posting) {
            PostingListDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                createdAt = createdAt
            )
        }
    }

    override fun getId(): Long = id
}
