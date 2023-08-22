package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class AnswerDeleteCommand(

    @field:NotNull(message = "adminId는 필수값 입니다. 작성자만 삭제할 수 있습니다.")
    @Schema(description = "삭제할 답변객체의  answerId", example = "1")
    val answerId: Long,

    @field:NotNull(message = "adminId는 필수값 입니다. 작성자만 삭제할 수 있습니다.")
    @Schema(description = "답글 작성자의  adminId", example = "1")
    val adminId: Long,

)
