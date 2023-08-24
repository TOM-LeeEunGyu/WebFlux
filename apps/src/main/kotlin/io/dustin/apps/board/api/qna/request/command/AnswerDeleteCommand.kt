package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

@Schema(description = "답변 삭제 요청 객체")
data class AnswerDeleteCommand(

    @Schema(description = "삭제할 답변객체의  answerId", example = "1")
    @field:Min(1, message = "answerId는 필수입니다. 최소값은 1입니다.")
    val answerId: Long,

    @Schema(description = "답글 작성자의  adminId", example = "1")
    @field:Min(1, message = "adminId는 필수입니다. 최소값은 1입니다.")
    val adminId: Long,

)
