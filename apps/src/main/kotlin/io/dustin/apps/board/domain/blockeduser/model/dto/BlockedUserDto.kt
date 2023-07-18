package io.dustin.apps.board.domain.blockeduser.model.dto

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import java.time.LocalDateTime

data class BlockedUserDto(
    val id: Long,
    val fromUserId: Long,
    val toUserId: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(blockedUser: BlockedUser) = with(blockedUser) {
            BlockedUserDto(
                id = id ?: 0L,
                fromUserId = fromUserId,
                toUserId = toUserId,
                createdAt = createdAt
            )
        }
    }
}
