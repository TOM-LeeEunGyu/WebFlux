package io.dustin.apps.board.domain.qna.answer.service

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.board.domain.qna.answer.repository.AnswerRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service

@Service
class ReadAnswerService(

    private val answerRepository: AnswerRepository

) {

    fun findByQuestionId(questionId: Long): Answer =
        answerRepository.findByQuestionId(questionId)


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

