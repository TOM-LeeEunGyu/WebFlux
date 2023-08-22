package io.dustin.apps.board.domain.qna.answer.service

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.repository.AnswerRepository
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class WriteAnswerService (
    private val answerRepository: AnswerRepository
) {
    /**
     * 답변 작성
     */
    fun create(adminId: Long, questionId: Long, content: String): Answer {
        val answer =  Answer(
            adminId = adminId,
            questionId = questionId,
            content = content
        )
        answerRepository.save(answer)
        return answer
    }

    /**
     * 답변 수정
     */
    fun updateContent(answer: Answer, content: String) {
        answer.updateContent(content)
    }

    /**
     * 답변 삭제
     */
    fun delete(answer: Answer) {
        answer.delete()
    }

    fun findByIdOrNull(answerId: Long): Answer? {
        return answerRepository.findByIdOrNull(answerId)
    }

    fun findByIdOrThrow(answerId: Long): Answer {
        return answerRepository.findByIdOrThrow(answerId)
    }
}
