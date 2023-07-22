package io.dustin.apps.board.domain.follow.model.dto

import io.dustin.apps.board.domain.follow.model.Follow
import java.time.LocalDateTime

data class FollowDto(
    val id: Long,
    val followerId: Long,
    val followingId: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(follow: Follow) = with(follow) {
            FollowDto(
                id = id ?: 0L,
                followerId = followerId,
                followingId = followingId,
                createdAt = createdAt
            )
        }
    }
}
