package io.dustin.apps.board.api.qna.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class QuestionDetailQuery(

    @Schema(description = "유저 고유 id 값")
    @field:NotNull(message = "userId는 필수입니다.")
    @field:NotBlank(message = "userId는 필수입니다.")
    val userId: Long,

    @Schema(description = "질문객체 고유 id 값")
    @field:NotNull(message = "questionId는 필수입니다.")
    @field:NotBlank(message = "questionId는 필수입니다.")
    val questionId: Long,

)