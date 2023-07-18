package io.dustin.apps.board.domain.community.comment.repository.custom.impl;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.dustin.apps.board.domain.community.comment.model.QComment;
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.board.domain.community.comment.repository.custom.CustomCommentRepository;
import io.dustin.apps.common.code.BoardType;
import io.dustin.apps.common.code.YesOrNo;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.types.Projections.constructor;
import static io.dustin.apps.board.domain.community.comment.model.QComment.comment;
import static io.dustin.apps.board.domain.community.posting.model.QPosting.posting;
import static io.dustin.apps.board.domain.like.model.QLike.like;

@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final JPAQueryFactory query;

    @Override
    public List<CommentDto> commentListByPosting(long userId, long postingId, int size, Long nextId) {
        QComment self = new QComment("self");
        JPAQuery<CommentDto> jPAQuery = query.select(constructor(CommentDto.class,
                                            comment.id,
                                            posting,
                                            comment.content,
                                            new CaseBuilder().when(like.id.isNotNull()).then(true).otherwise(false).as("isLike"),
                                            new CaseBuilder().when(self.replyId.isNotNull()).then(true).otherwise(false).as("isReply"),
                                            comment.userId,
                                            comment.postingId,
                                            comment.replyId,
                                            comment.createdAt
                                        )).
                                        distinct()
                                        .from(posting)
                                        .leftJoin(comment).on(comment.postingId.eq(posting.id))
                                        .leftJoin(self).on(self.replyId.eq(comment.id).and(self.replyId.isNotNull()))
                                        .leftJoin(like).on(
                                                like.boardType.eq(BoardType.COMMENT)
                                                .and(like.boardId.eq(comment.id))
                                                .and(like.userId.eq(userId))

                                        )
                                        .where(
                                            posting.id.eq(postingId),
                                            comment.replyId.isNull(),
                                            comment.indexByCountPagination(nextId)
                                        )
                                        .orderBy(comment.id.desc())
                                        .limit(size);
        return jPAQuery.fetch();
    }

    @Override
    public List<CommentDto> replyListByComment(long userId, long commentId, int size, Long nextId) {
        QComment self = new QComment("self");
        JPAQuery<CommentDto> jPAQuery = query.select(constructor(CommentDto.class,
                                            comment.id,
                                            posting,
                                            comment.content,
                                            new CaseBuilder().when(like.id.isNotNull()).then(true).otherwise(false).as("isLike"),
                                            new CaseBuilder().when(self.replyId.isNotNull()).then(true).otherwise(false).as("isReply"),
                                            comment.userId,
                                            comment.postingId,
                                            comment.replyId,
                                            comment.createdAt
                                        ))
                                        .from(comment)
                                        .leftJoin(self).on(self.replyId.eq(comment.id).and(self.replyId.isNotNull()))

                                        .leftJoin(posting).on(comment.postingId.eq(posting.id))

                                        .leftJoin(like).on(
                                                like.boardType.eq(BoardType.COMMENT)
                                                .and(like.boardId.eq(comment.id))
                                                .and(like.userId.eq(userId))

                                        )
                                        .where(
                                            comment.replyId.eq(commentId),
                                            comment.isDeleted.ne(YesOrNo.Y),
                                            comment.indexByCountPagination(nextId)
                                        )
                                        .orderBy(comment.id.desc())
                                        .limit(size);
        return jPAQuery.fetch();
    }
}
