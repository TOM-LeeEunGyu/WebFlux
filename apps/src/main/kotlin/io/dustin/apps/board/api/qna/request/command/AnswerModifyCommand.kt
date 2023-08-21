package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class AnswerModifyCommand(

    @field:NotNull(message = "adminId는 필수값 입니다.")
    @Schema(description = "답글을 작성자의  adminId", example = "1")
    val adminId: Long,


    @field:NotNull(message = "questionId 필수값 입니다.")
    @Schema(description = "답글을 작성할 질문에 대한 id값", example = "1")
    val questionId: Long,

    @field:NotNull(message = "답글 내용은 필수입니다.")
    @field:Min(5, message = "답글은 최소 5자 이상 작성해야 합니다")
    @Schema(description = "답글")
    val content: String,
)

