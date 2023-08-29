package io.dustin.apps.board.domain.qna.answer.service

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.repository.AnswerRepository
import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReadAnswerService(

    private val answerRepository: AnswerRepository

) {

    fun getAnswerObj(questionId: Long): Answer? {
        return answerRepository.findByQuestionId(questionId)
    }

    /**
     * questionId로 Answer 객체를 찾는다.
     */
    fun findByQuestionId(questionId: Long): Answer {
        val errorMessage = "질문객체 고유값[$questionId]로 조회된 정보가 없습니다."
        return answerRepository.findByQuestionId(questionId) ?: notFoundEntity(errorMessage)
    }

    fun findByIdOrNull(answerId: Long): Answer? {
        return answerRepository.findByIdOrNull(answerId)
    }

    fun findByIdOrThrow(answerId: Long): Answer {
        return answerRepository.findByIdOrThrow(answerId)
    }
}

