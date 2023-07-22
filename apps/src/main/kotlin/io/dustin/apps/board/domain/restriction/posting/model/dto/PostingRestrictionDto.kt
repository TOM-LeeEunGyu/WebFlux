package io.dustin.apps.board.domain.restriction.posting.model.dto

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import java.time.LocalDateTime

data class PostingRestrictionDto(
    val id: Long,
    val fromUserId: Long,
    val toUserId: Long,
    val postingId: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(postingRestriction: PostingRestriction) = with(postingRestriction) {
            PostingRestrictionDto(
                id = id ?: 0L,
                fromUserId = fromUserId,
                toUserId = toUserId,
                postingId = postingId,
                createdAt = createdAt
            )
        }
    }
}
