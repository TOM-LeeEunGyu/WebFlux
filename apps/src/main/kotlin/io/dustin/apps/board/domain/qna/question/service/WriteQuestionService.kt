package io.dustin.apps.board.domain.qna.question.service

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service

@Service
class WriteQuestionService (
    private val questionRepository: QuestionRepository
) {

    fun create(userId: Long, subject: String, content: String): Question {
        val question = Question(
            userId = userId,
            subject =  subject,
            content = content,

        )
        return questionRepository.save<Question>(question)
    }

    fun updateContent(question: Question, subject: String, content: String) {
        question.updateSubject(subject)
        question.updateContent(content)
        questionRepository.save<Question>(question)
    }

    fun delete(question: Question) {
        question.delete()
    }

    fun click(questionId: Long) {
        val question = findByIdOrThrow(questionId)
        val count: Long = question.clickCount + 1
        question.updateClickCount(count)
    }

    fun findById(postingId: Long): Question {
        return questionRepository.findById(postingId).orElse(null)
    }

    fun findByIdOrThrow(postingId: Long): Question {
        return questionRepository.findById(postingId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", postingId.toString()))
            }
    }
}
