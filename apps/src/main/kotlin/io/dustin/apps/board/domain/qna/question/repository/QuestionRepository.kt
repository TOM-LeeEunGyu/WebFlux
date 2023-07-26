package io.dustin.apps.board.domain.qna.question.repository

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.repository.custom.CustomQuestionRepository
import io.dustin.apps.common.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import java.util.*

interface QuestionRepository : BaseRepository<Question, Long>, CustomQuestionRepository {

    fun findBySubject(subject: String): Question?

    fun findBySubjectAndContent(subject: String, content: String): Question?

    fun findBySubjectLike(subject: String): List<Question>
    override fun findAll(pageable: Pageable): Page<Question>
    override fun findAll(spec: Specification<Question>, pageable: Pageable): Page<Question>
}
