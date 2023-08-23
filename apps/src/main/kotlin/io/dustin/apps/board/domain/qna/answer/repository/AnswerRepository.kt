package io.dustin.apps.board.domain.qna.answer.repository

import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.common.repository.BaseRepository

interface AnswerRepository : BaseRepository<Answer, Long> {
    fun findByQuestionId(questionId: Long): Answer?
}
