package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "댓글 작성 관련 요청 객체")
class CommentCreateCommand(

    @Schema(description = "댓글 작성자의  userId", example = "1")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "대댓글(받아오는 댓글 id값의 하위 댓글로 작성됨)", example = "1")
    val replyId: Long?,

    @Schema(description = "댓글을 작성할 postingId", example = "1")
    @field:Min(1, message = "postingId는 필수입니다. 최소값은 1입니다.")
    val postingId: Long,

    @Schema(description = "댓글 내용", example = "어쩌구 저쩌구 ~")
    @field:NotNull(message = "content는 필수입니다.")
    @field:NotBlank(message = "content는 필수입니다.")
    val content: String,

)