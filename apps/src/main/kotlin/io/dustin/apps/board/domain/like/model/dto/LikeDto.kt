package io.dustin.apps.board.domain.like.model.dto

import io.dustin.apps.board.domain.like.model.Like
import io.dustin.apps.common.code.BoardType
import java.time.LocalDateTime

class LikeDto(
    val id: Long,
    val userId: Long,
    val boardId: Long,
    val boardType: BoardType,
    val createdAt: LocalDateTime
    ) {
    companion object {

        fun from(like: Like) = with(like) {
            LikeDto(
                id = id ?: 0L,
                userId = userId,
                boardId = boardId,
                boardType = boardType,
                createdAt = createdAt
            )
        }

    }
}
