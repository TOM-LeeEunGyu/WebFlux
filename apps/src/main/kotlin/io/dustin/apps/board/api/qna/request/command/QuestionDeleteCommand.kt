package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class QuestionDeleteCommand(
    @field:NotNull(message = "userId 필수값 입니다. 작성자만 삭제할 수 있습니다.")
    @Schema(description = "질문 작성자의  userId", example = "1")
    val userId: Long,
)
