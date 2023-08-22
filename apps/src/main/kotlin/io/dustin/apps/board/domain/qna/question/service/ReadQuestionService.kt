package io.dustin.apps.board.domain.qna.question.service

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import io.dustin.apps.common.utils.notFoundEntity
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ReadQuestionService (

    private val questionRepository: QuestionRepository
) {

    /**
     * 질문 객체(단일) 가져오기
     */
    fun getQuestion(userId: Long, questionId: Long): QuestionDto {
        val errorMessage = "질문객체 고유값[$questionId]로 조회된 정보가 없습니다."
        return questionRepository.getQuestion(userId, questionId) ?: notFoundEntity(errorMessage)
    }


    /**
     * 질문 객체(리스트) 가져오기
     */
    fun getQuestionList(userId: Long, recordsCount: Long, nextId: Long?): List<QuestionDto> =
        questionRepository.getQuestionList(userId, recordsCount, nextId)


    fun findByIdOrNull(questionId: Long): Question? {
        return questionRepository.findByIdOrNull(questionId)
    }

    fun findByIdOrThrow(questionId: Long): Question {
        return questionRepository.findByIdOrThrow(questionId)
    }
}
