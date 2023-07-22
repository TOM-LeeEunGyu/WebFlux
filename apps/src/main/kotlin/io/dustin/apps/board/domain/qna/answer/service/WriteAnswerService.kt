package io.dustin.apps.board.domain.qna.answer.service

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.repository.AnswerRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service

@Service
class WriteAnswerService (
    private val answerRepository: AnswerRepository
) {
    fun create(adminId: Long, questionId: Long, content: String): Answer {
        val answer =  Answer(
            adminId = adminId,
            questionId = questionId,
            content = content
        )
        answerRepository.save<Answer>(answer)
        return answer
    }

    fun updateContent(answer: Answer, content: String) {
        answer.updateContent(content)
    }

    fun delete(answer: Answer) {
        answer.delete()
    }

    fun findById(postingId: Long): Answer {
        return answerRepository.findById(postingId).orElse(null)
    }

    fun findByIdOrThrow(postingId: Long): Answer {
        return answerRepository.findById(postingId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", postingId.toString()))
            }
    }
}
