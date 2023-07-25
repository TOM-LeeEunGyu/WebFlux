package io.dustin.apps.board.domain.qna.question.repository.custom.impl

import com.querydsl.core.types.ConstructorExpression
import com.querydsl.core.types.Projections.constructor
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import io.dustin.apps.board.domain.qna.answer.model.QAnswer
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.model.QQuestion
import io.dustin.apps.board.domain.qna.question.repository.custom.CustomQuestionRepository
import io.dustin.apps.common.code.YesOrNo
import org.springframework.stereotype.Repository

@Repository
class CustomQuestionRepositoryImpl(private val query: JPAQueryFactory) : CustomQuestionRepository {

    override fun getQuestion(userId: Long, questionId: Long): QuestionDto? {
        val question = QQuestion.question
        val answer = QAnswer.answer

        val jPAQuery: JPAQuery<QuestionDto> = query.select(
            constructor(
                QuestionDto::class.java,
                question.id,
                question.userId,
                question.subject,
                question.content,
                CaseBuilder().`when`(answer.id.isNotNull).then(true).otherwise(false).`as`("isComment"),
                question.createdAt
            )
        )
            .from(question)
            .leftJoin(answer).on(
                answer.questionId.eq(question.id)
            )
            .where(
                question.id.eq(questionId),
                question.userId.eq(userId),
                question.isDeleted.ne(YesOrNo.Y)
            )

        return jPAQuery.fetchOne()
    }

    override fun getQuestionList(userId: Long, nextId: Long?, size: Int): List<QuestionDto> {
        val question = QQuestion.question
        val answer = QAnswer.answer

        val jPAQuery: JPAQuery<QuestionDto> = query.select(
            constructor(
                QuestionDto::class.java,
                question.id,
                question.userId,
                question.subject,
                question.content,
                CaseBuilder().`when`(answer.id.isNotNull).then(true).otherwise(false).`as`("isComment"),
                question.createdAt
            )
        )
            .from(question)
            .leftJoin(answer).on(
                answer.questionId.eq(question.id)
            )
            .where(
                question.userId.eq(userId),
                question.isDeleted.ne(YesOrNo.Y)
            )

        return jPAQuery.fetch()
    }
}
