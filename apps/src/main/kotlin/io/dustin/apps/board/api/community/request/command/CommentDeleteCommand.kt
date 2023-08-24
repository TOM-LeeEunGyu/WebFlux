package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class CommentDeleteCommand(

    @Schema(description = "댓글 작성자의  userId", example = "1")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "댓글을 삭제할 commentId", example = "1")
    @field:Min(1, message = "commentId는 필수입니다. 최소값은 1입니다.")
    val commentId: Long,

)