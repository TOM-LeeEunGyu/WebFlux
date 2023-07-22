package io.dustin.apps.board.domain.bookmark.model.dto

import io.dustin.apps.board.domain.bookmark.model.Bookmark
import java.time.LocalDateTime

data class BookMarkDto(
    val id: Long,
    val userId: Long,
    val boardId: Long,
    val createdAt: LocalDateTime
) {
    companion object {

        fun from(bookmark: Bookmark) = with(bookmark) {
            BookMarkDto(
                id = id ?: 0L,
                userId = userId,
                boardId = boardId,
                createdAt = createdAt
            )
        }
    }
}
