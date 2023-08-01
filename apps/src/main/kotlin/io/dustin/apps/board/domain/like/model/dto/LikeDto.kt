package io.dustin.apps.board.domain.like.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.like.model.Like
import io.dustin.apps.common.code.BoardType
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 좋아요 dto
 */
@Schema(description = "좋아요 관련 응답 객체")
class LikeDto(
    @Schema(description = "id")
    val id: Long,
    @Schema(description = "유저 id")
    val userId: Long,
    @Schema(description = "게시물 id")
    val boardId: Long,
    @Schema(description = "게시물 타입(게시물,댓글,공지 등)")
    val boardType: BoardType,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
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
