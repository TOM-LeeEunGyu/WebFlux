package io.dustin.apps.board.domain.qna.question.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.common.model.IdAble
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 1:1 문의 질문 dto
 */
@Schema(description = "1:1 문의 질문 관련 응답 객체")
data class QuestionDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "질문을 작성한 유저 id")
    val userId: Long,

    @Schema(description = "질문 제목")
    var subject: String,

    @Schema(description = "질문 내용")
    var content: String,

    @Schema(description = "답변 여부")
    var isAnswer: Boolean,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime
) : IdAble {


    companion object {
        fun from(question: Question) = with(question) {
            QuestionDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                isAnswer = false,
                createdAt = createdAt

            )
        }
    }
    override fun id(): Long = id
}
