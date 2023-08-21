package io.dustin.apps.board.api.qna.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class QuestionCommand(

    @field:NotNull(message = "userId 필수값 입니다.")
    @Schema(description = "질문 작성자의  userId", example = "1")
    val userId: Long,


    @field:NotNull(message = "subject는 필수값 입니다.")
    @field:Min(5, message = "제목은 최소 5자 이상 작성해야 합니다")
    val subject: String,

    @field:NotNull(message = "답글 내용은 필수입니다.")
    @field:Min(5, message = "답글은 최소 5자 이상 작성해야 합니다")
    @Schema(description = "답글")
    val content: String,


    ) {
}