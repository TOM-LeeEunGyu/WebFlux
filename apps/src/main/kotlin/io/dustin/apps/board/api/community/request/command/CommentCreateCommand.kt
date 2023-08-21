package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "댓글 작성 관련 요청 객체")
class CommentCreateCommand(
    @Schema(description = "댓글을 작성하는 유저정보", example = "1")
    @field:NotBlank(message = "유저 id 정보는 필수입니다.")
    val userId: Long,

    @Schema(description = "대댓글(받아오는 댓글 id값의 하위 댓글로 작성됨)", example = "1")
    val replyId: Long?,

    @field:NotNull(message = "postingId는 필수값 입니다.")
    @Schema(description = "댓글을 작성하려는 게시물의 id값", example = "1")
    val postingId: Long,

    @Schema(description = "댓글 내용")
    @field:Min(5, message = "댓글은 최소 5자이상 작성해야 합니다")
    val content: String,

)