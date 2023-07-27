package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.IdAble
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class PostingDto(
    val id: Long,
    var userId: Long,
    val subject: String,
    val content: String,
    var isLike: Boolean,
    var isBookmark: Boolean,
    var following: Boolean,
    var follower: Boolean,
    var followingAndFollower: Boolean,
    var commentCnt: Long,
    var clickCnt: Long,
    var likeCount: Long,
    val createdAt: LocalDateTime = now(),
    var updatedAt: LocalDateTime? = null,

    ): IdAble {

    companion object {
        fun from(posting: Posting) = with(posting) {
            PostingDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                isLike =  false,
                isBookmark = false,
                following = false,
                follower = false,
                followingAndFollower = false,
                commentCnt = 0,
                clickCnt = 0,
                likeCount = 0,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
    override fun id(): Long = id
}
