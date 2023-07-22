package io.dustin.apps.board.domain.qna.question.repository.custom.impl
//
//import com.querydsl.core.types.dsl.CaseBuilder
//
//@RequiredArgsConstructor
//class CustomQuestionRepositoryImpl : CustomQuestionRepository {
//    private val query: JPAQueryFactory? = null
//    override fun getQuestion(userId: Long, questionId: Long): QuestionDto {
//        val jPAQuery: JPAQuery<QuestionDto> = query.select(
//            constructor(
//                QuestionDto::class.java,
//                question.id,
//                question.userId,
//                question.subject,
//                question.content,
//                CaseBuilder().`when`(answer.id.isNotNull()).then(true).otherwise(false).`as`("isComment"),
//                question.createdAt
//            )
//        )
//            .from(question)
//            .leftJoin(answer).on(
//                answer.questionId.eq(question.id)
//            )
//            .where(
//                question.id.eq(questionId),
//                question.userId.eq(userId),
//                question.isDeleted.ne(YesOrNo.Y)
//            )
//        return jPAQuery.fetchOne()
//    }
//
//    override fun getQuestionList(userId: Long, nextId: Long?, size: Int): List<QuestionDto?> {
//        val jPAQuery: JPAQuery<QuestionDto> = query.select(
//            constructor(
//                QuestionDto::class.java,
//                question.id,
//                question.userId,
//                question.subject,
//                question.content,
//                CaseBuilder().`when`(answer.id.isNotNull()).then(true).otherwise(false).`as`("isComment"),
//                question.createdAt
//            )
//        )
//            .from(question)
//            .leftJoin(answer).on(
//                answer.questionId.eq(question.id)
//            )
//            .where(
//                question.userId.eq(userId),
//                question.isDeleted.ne(YesOrNo.Y)
//            )
//        return jPAQuery.fetch()
//    }
//}
