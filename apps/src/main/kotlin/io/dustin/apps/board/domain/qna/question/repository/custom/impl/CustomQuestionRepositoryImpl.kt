package io.dustin.apps.board.domain.qna.question.repository.custom.impl

import com.querydsl.core.types.ConstructorExpression
import com.querydsl.core.types.Projections.constructor
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import io.dustin.apps.board.domain.qna.answer.model.QAnswer
import io.dustin.apps.board.domain.qna.answer.model.QAnswer.answer
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.model.QQuestion
import io.dustin.apps.board.domain.qna.question.model.QQuestion.question
import io.dustin.apps.board.domain.qna.question.repository.custom.CustomQuestionRepository
import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.utils.dataNotFound
import org.springframework.stereotype.Repository

@Repository
class CustomQuestionRepositoryImpl(
    private val query: JPAQueryFactory) : CustomQuestionRepository {

    override fun getQuestion(userId: Long, questionId: Long
    ): QuestionDto {
        return query.select(
            constructor(
                QuestionDto::class.java,
                question.id,
                question.userId,
                question.subject,
                question.content,
                CaseBuilder().`when`(answer.id.isNotNull).then(true).otherwise(false).`as`("isAnswer"),
                question.createdAt
            ))
            .from(question)
            .leftJoin(answer).on(
                answer.questionId.eq(question.id)
            )
            .where(
                question.id.eq(questionId),
                question.userId.eq(userId),
                question.isDeleted.ne(YesOrNo.Y)
            )
            .fetchOne() ?: dataNotFound("존재하지 않는 질문입니다.")
    }

    override fun getQuestionList(userId: Long, recordsCount: Long, nextId: Long?):
            List<QuestionDto> {
            return query.select(
            constructor(
                QuestionDto::class.java,
                question.id,
                question.userId,
                question.subject,
                question.content,
                CaseBuilder().`when`(answer.id.isNotNull).then(true).otherwise(false).`as`("isComment"),
                question.createdAt
            ))
            .from(question)
            .leftJoin(answer).on(
                answer.questionId.eq(question.id)
            )
            .where(
                question.userId.eq(userId),
                question.isDeleted.ne(YesOrNo.Y)
            )
            .limit(recordsCount + 1)
            .fetch()
    }
}
