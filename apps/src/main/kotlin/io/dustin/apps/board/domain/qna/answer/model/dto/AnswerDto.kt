package io.dustin.apps.board.domain.qna.answer.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 1:1 문의 답변 dto
 */
@Schema(description = "1:1 문의 답변 관련 응답 객체")
data class AnswerDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "답변 내용")
    val content: String,

    @Schema(description = "답변을 작성한 어드민 id")
    val adminId: Long,

    @Schema(description = "답변을 작성한 질문 객체 id")
    val questionId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(answer: Answer) = with(answer) {
            AnswerDto(
                id = id ?: 0L,
                content = content,
                adminId = adminId,
                questionId = questionId,
                createdAt = createdAt
            )
        }
    }
}