package io.dustin.apps.board.domain.qna.question.repository.custom.impl;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto;
import io.dustin.apps.board.domain.qna.question.repository.custom.CustomQuestionRepository;
import io.dustin.apps.common.code.YesOrNo;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static io.dustin.apps.board.domain.qna.answer.model.QAnswer.answer;
import static io.dustin.apps.board.domain.qna.question.model.QQuestion.question;

@RequiredArgsConstructor
public class CustomQuestionRepositoryImpl implements CustomQuestionRepository {

    private final JPAQueryFactory query;

    @Override
    public QuestionDto getQuestion(long userId, long questionId) {


        JPAQuery<QuestionDto> jPAQuery = query.select(constructor(QuestionDto.class,
                        question.id,
                        question.userId,
                        question.subject,
                        question.content,
                        new CaseBuilder().when(answer.id.isNotNull()).then(true).otherwise(false).as("isComment"),
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
                );

        return jPAQuery.fetchOne();
    }

    @Override
    public List<QuestionDto> getQuestionList(long userId, Long nextId, int size) {


        JPAQuery<QuestionDto> jPAQuery = query.select(constructor(QuestionDto.class,
                        question.id,
                        question.userId,
                        question.subject,
                        question.content,
                        new CaseBuilder().when(answer.id.isNotNull()).then(true).otherwise(false).as("isComment"),
                        question.createdAt
                ))
                .from(question)
                .leftJoin(answer).on(
                        answer.questionId.eq(question.id)
                )
                .where(
                        question.userId.eq(userId),
                        question.isDeleted.ne(YesOrNo.Y)
                );

        return jPAQuery.fetch();
    }
}
