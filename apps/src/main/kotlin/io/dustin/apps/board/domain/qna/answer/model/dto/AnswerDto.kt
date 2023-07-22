package io.dustin.apps.board.domain.qna.answer.model.dto

import io.dustin.apps.board.domain.qna.answer.model.Answer
import java.time.LocalDateTime

data class AnswerDto(
    val id: Long,
    val content: String,
    val adminId: Long,
    val questionId: Long,
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