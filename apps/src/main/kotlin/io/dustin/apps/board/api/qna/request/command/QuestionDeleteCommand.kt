package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "질문 삭제 요청 객체")
data class QuestionDeleteCommand(

    @Schema(description = "질문 작성자의  userId", example = "1")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "질문객체 id 값", example = "1")
    @field:Min(1, message = "questionId는 필수입니다. 최소값은 1입니다.")
    val questionId: Long,
)
