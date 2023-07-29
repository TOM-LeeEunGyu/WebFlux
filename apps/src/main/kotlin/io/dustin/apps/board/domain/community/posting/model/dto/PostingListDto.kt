package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.BaseEntity
import io.dustin.apps.common.model.IdAble
import java.time.LocalDateTime

data class PostingListDto(
    val id: Long,
    val userId: Long,
    val subject: String,
    val content: String,
    var isLike: Boolean,
    var isBookmark: Boolean,
    var commentCnt: Long,
    var clickCnt: Long,
    var likeCount: Long,
    val createdAt: LocalDateTime? = LocalDateTime.now(),
) : IdAble {
    companion object {
        fun from(posting: Posting) =  with(posting) {
            PostingListDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                isLike = false, // Set default value for isLike
                isBookmark = false, // Set default value for isBookmark
                commentCnt = 0, // Set default value for commentCnt
                clickCnt = 0, // Set default value for clickCnt
                likeCount = 0, // Set default value for likeCount
                createdAt = createdAt,

            )
        }
    }

    override fun id(): Long = id
}
