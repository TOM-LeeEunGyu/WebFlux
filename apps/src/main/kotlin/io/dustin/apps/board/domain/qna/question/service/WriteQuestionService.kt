package io.dustin.apps.board.domain.qna.question.service

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class WriteQuestionService (
    private val questionRepository: QuestionRepository
) {

    /**
     * 질문 생성
     */
    fun create(userId: Long, subject: String, content: String): Question {
        val question = Question(
            userId = userId,
            subject =  subject,
            content = content,

        )
        return questionRepository.save(question)
    }

    /**
     * 질문 수정
     */
    fun updateContent(question: Question, subject: String, content: String) {
        question.updateSubject(subject)
        question.updateContent(content)
        questionRepository.save<Question>(question)
    }

    /**
     * 질문 삭제
     */
    fun delete(question: Question) {
        question.delete()
    }


    /**
     * 질문 조회수 증가
     */
    fun click(questionId: Long) {
        val question = findByIdOrThrow(questionId)
        val count: Long = question.clickCount + 1
        question.updateClickCount(count)
    }

    fun findByIdOrNull(questionId: Long): Question? {
        return questionRepository.findByIdOrNull(questionId)
    }

    fun findByIdOrThrow(questionId: Long): Question {
        return questionRepository.findByIdOrThrow(questionId)
    }
}
