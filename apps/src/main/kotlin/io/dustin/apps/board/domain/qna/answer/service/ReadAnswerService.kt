package io.dustin.apps.board.domain.qna.answer.service

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.repository.AnswerRepository
import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReadAnswerService(

    private val answerRepository: AnswerRepository

) {

    fun findByQuestionId(questionId: Long): Answer =
        answerRepository.findByQuestionId(questionId)


    fun findByIdOrNull(answerId: Long): Answer? {
        return answerRepository.findByIdOrNull(answerId)
    }

    fun findByIdOrThrow(answerId: Long): Answer {
        return answerRepository.findByIdOrThrow(answerId)
    }
}

