package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Schema(description = "댓글 작성 관련 요청 객체")
class CommentCreateCommand(
    @Schema(description = "유저정보")
    @field:NotBlank(message = "유저 id 정보는 필수입니다.")
    val userId: Long,
    val replyId: Long?,
    @Schema(description = "댓글")
    @field:Min(5, message = "댓글은 최소 5자이상 작성해야 합니다")
    val content: String,

) {
}