package io.dustin.apps.board.domain.bookmark.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.bookmark.model.Bookmark
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 북마크 dto
 */
@Schema(description = "차단유저 관련 응답 객체")
data class BookMarkDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "유저 id 값")
    val userId: Long,

    @Schema(description = "북마크에 추가하려는 게시물 id 값")
    val boardId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
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
