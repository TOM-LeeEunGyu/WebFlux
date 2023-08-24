package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "댓글 수정 관련 요청 객체")
class CommentModifyCommand(

    @Schema(description = "댓글 작성자의  userId", example = "1")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "댓글을 삭제할 commentId", example = "1")
    @field:Min(1, message = "commentId는 필수입니다. 최소값은 1입니다.")
    val commentId: Long,

    @Schema(description = "댓글 내용", example = "어쩌구 저쩌구 ~")
    @field:NotNull(message = "content는 필수입니다.")
    @field:NotBlank(message = "content는 필수입니다.")
    val content: String,

)