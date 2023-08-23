package io.dustin.apps.board.domain.like.model.dto

import io.dustin.apps.common.code.BoardType
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 좋아요 create dto
 */
@Schema(description = "클라이언트에서 넘어오는 boardType에 따라 좋아요 객체를 생성한다")
class LikeItCommand (
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "게시물(댓글,공지) id")
    val boardId: Long,

    @Schema(description = "좋아요를 누른 유저 id")
    val userId: Long,

    @Schema(description = "게시물(댓글,공지) 타입")
    val boardType: BoardType,


)


