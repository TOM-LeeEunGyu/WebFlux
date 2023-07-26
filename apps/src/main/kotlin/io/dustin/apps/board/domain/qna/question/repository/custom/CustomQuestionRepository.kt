package io.dustin.apps.board.domain.qna.question.repository.custom

import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto

interface CustomQuestionRepository {
    fun getQuestion(userId: Long, questionId: Long): QuestionDto?
    fun getQuestionList(userId: Long, nextId: Int?, size: Int): List<QuestionDto>
}
