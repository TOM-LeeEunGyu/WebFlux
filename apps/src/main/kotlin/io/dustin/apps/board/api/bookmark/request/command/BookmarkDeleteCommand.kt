package io.dustin.apps.board.api.bookmark.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Schema(description = "북마크 삭제 관련 객체")
class BookmarkDeleteCommand(

    @Schema(description = "로그인한 유저 id")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "북마크에 추가할 게시물 id")
    @field:Min(1, message = "boardId는 필수입니다. 최소값은 1입니다.")
    val boardId: Long,
) {
}