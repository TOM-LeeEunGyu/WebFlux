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

    fun findByAnswerId(answerId: Long): Answer {
        val errorMessage = "답변객체 고유값[$answerId]로 조회된 정보가 없습니다."
        return answerRepository.findByAnswerId(answerId) ?: notFoundEntity()
    }

    fun findByIdOrNull(answerId: Long): Answer? {
        return answerRepository.findByIdOrNull(answerId)
    }

    fun findByIdOrThrow(answerId: Long): Answer {
        return answerRepository.findByIdOrThrow(answerId)
    }
}

