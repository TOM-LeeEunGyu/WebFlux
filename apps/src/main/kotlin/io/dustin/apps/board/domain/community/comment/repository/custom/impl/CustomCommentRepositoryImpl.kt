package io.dustin.apps.board.domain.community.comment.repository.custom.impl

import com.querydsl.core.types.Projections.constructor
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import io.dustin.apps.board.domain.community.comment.model.*
import io.dustin.apps.board.domain.community.comment.model.QComment.*
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.repository.custom.CustomCommentRepository
import io.dustin.apps.board.domain.community.posting.model.QPosting.posting
import io.dustin.apps.board.domain.like.model.QLike.like
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.model.extension.CommentExtension

class CustomCommentRepositoryImpl(
    private val query: JPAQueryFactory): CustomCommentRepository {

    override fun commentListByPosting(userId: Long, postingId: Long, recordsCount: Long, nextId: Long?): List<CommentDto> {
        val self = QComment("self")
        val jPAQuery: JPAQuery<CommentDto> = query.select(
            constructor(
                CommentDto::class.java,
                comment.id,
                posting,
                comment.content,
                CaseBuilder().`when`(like.id.isNotNull).then(true).otherwise(false).`as`("isLike"),
                CaseBuilder().`when`(self.replyId.isNotNull).then(true).otherwise(false).`as`("isReply"),
                comment.userId,
                comment.postingId,
                comment.replyId,
                comment.createdAt
            ))
            .distinct()
            .from(posting)
            .leftJoin(comment).on(comment.postingId.eq(posting.id))
            .leftJoin(self).on(self.replyId.eq(comment.id).and(self.replyId.isNotNull))
            .leftJoin(like).on(
                like.boardType.eq(BoardType.COMMENT)
                    .and(like.boardId.eq(comment.id))
                    .and(like.userId.eq(userId))
            )
            .where(
                posting.id.eq(postingId),
                comment.replyId.isNull,
                comment.indexByCountPagination(nextId)
            )
            .orderBy(comment.id.desc())
            .limit(recordsCount+1)
        return jPAQuery.fetch()
    }

    override fun replyListByComment(userId: Long, commentId: Long, recordsCount: Long, nextId: Long?): List<CommentDto> {
        val builder = CommentExtension.indexByCountPagination(comment, nextId)
        val self = QComment("self")
        val jPAQuery: JPAQuery<CommentDto> = query.select(
            constructor(
                CommentDto::class.java,
                comment.id,
                posting,
                comment.content,
                CaseBuilder().`when`(like.id.isNotNull).then(true).otherwise(false).`as`("isLike"),
                CaseBuilder().`when`(self.replyId.isNotNull).then(true).otherwise(false).`as`("isReply"),
                comment.userId,
                comment.postingId,
                comment.replyId,
                comment.createdAt
            ))
            .from(comment)
            .leftJoin(self).on(self.replyId.eq(comment.id).and(self.replyId.isNotNull))
            .leftJoin(posting).on(comment.postingId.eq(posting.id))
            .leftJoin(like).on(
                like.boardType.eq(BoardType.COMMENT)
                    .and(like.boardId.eq(comment.id))
                    .and(like.userId.eq(userId))
            )
            .where(
                comment.replyId.eq(commentId),
                comment.isDeleted.ne(YesOrNo.Y),
                builder
            )
            .orderBy(comment.id.desc())
            .limit(recordsCount+1)
        return jPAQuery.fetch()
    }
}
