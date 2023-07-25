package io.dustin.apps.board.domain.qna.question.model.dto

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.common.model.IdAble
import java.time.LocalDateTime

data class QuestionDto(
    val id: Long,
    val userId: Long,
    var subject: String,
    var content: String,
    val createdAt: LocalDateTime
) : IdAble {

    var isComment: Boolean = false
    companion object {
        fun from(question: Question) = with(question) {
            QuestionDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                createdAt = createdAt

            )
        }
    }
    override fun id(): Long = id
}
