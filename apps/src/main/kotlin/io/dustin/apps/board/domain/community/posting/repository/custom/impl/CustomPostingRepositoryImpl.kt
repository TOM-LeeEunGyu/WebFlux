package io.dustin.apps.board.domain.community.posting.repository.custom.impl

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections.constructor
import com.querydsl.core.types.dsl.CaseBuilder
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.jpa.impl.JPAQueryFactory
import io.dustin.apps.board.domain.blockeduser.service.ReadBlockedUserService
import io.dustin.apps.board.domain.bookmark.model.QBookmark.bookmark
import io.dustin.apps.board.domain.community.posting.model.QPosting.posting
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto
import io.dustin.apps.board.domain.community.posting.repository.custom.CustomPostingRepository
import io.dustin.apps.board.domain.follow.service.ReadFollowService
import io.dustin.apps.board.domain.like.model.QLike.like
import io.dustin.apps.common.code.BoardType
import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.exception.DataNotFoundException

class CustomPostingRepositoryImpl(
    private val query: JPAQueryFactory,
    private val readBlockedUserService: ReadBlockedUserService,
    private val readFollowService: ReadFollowService
) : CustomPostingRepository {

    override fun getPosting(userId: Long, postingId: Long): PostingDto {
        val selectedPosting = query.selectFrom(posting).where(posting.id.eq(postingId)).fetchOne()
        val postingAuthorId = selectedPosting?.userId ?: throw DataNotFoundException("데이터가 없습니다.")

        val followingIds = readFollowService.followingIds(postingAuthorId)
        val isFollowing = followingIds.contains(userId)

        val followerIds = readFollowService.followerIds(postingAuthorId)
        val isFollower = followerIds.contains(userId)
        val isFollowingAndFollower = isFollowing || isFollower

        val jPAQuery = query.select(
            constructor(
                PostingDto::class.java,
                posting.id,
                posting.userId,
                posting.subject,
                posting.content,
                CaseBuilder().`when`(like.id.isNotNull).then(true).otherwise(false).`as`("isLike"),
                CaseBuilder().`when`(bookmark.id.isNotNull).then(true).otherwise(false).`as`("isBookmark"),
                Expressions.constant(isFollowing),
                Expressions.constant(isFollower),
                Expressions.constant(isFollowingAndFollower),
                posting.commentCount.`as`("commentCnt"),
                posting.clickCount.`as`("clickCnt"),
                posting.likeCount,
                posting.createdAt
            )
        )
            .from(posting)
            .leftJoin(like).on(
                like.boardType.eq(BoardType.POSTING)
                    .and(like.boardId.eq(posting.id))
                    .and(like.userId.eq(userId))
            )
            .leftJoin(bookmark).on(
                bookmark.boardId.eq(posting.id)
                    .and(bookmark.userId.eq(userId))
            )
            .where(
                posting.id.eq(postingId)
            )

        return jPAQuery.fetchOne() ?: throw DataNotFoundException("데이터가 없습니다.")
    }

    override fun getPostingList(userId: Long,
                                recordsCount: Long,
                                nextId: Long?): List<PostingListDto> {
        val booleanBuilder = BooleanBuilder()
        val toUserIds = readBlockedUserService.toUserIds(userId)
        val fromUserIds = readBlockedUserService.fromUserIds(userId)

        booleanBuilder.and(posting.userId.notIn(toUserIds).and(posting.userId.notIn(fromUserIds)))
        return  query.select(
            constructor(
                PostingListDto::class.java,
                posting.id,
                posting.userId,
                posting.subject,
                posting.content,
                CaseBuilder().`when`(like.id.isNotNull).then(true).otherwise(false).`as`("isLike"),
                CaseBuilder().`when`(bookmark.id.isNotNull).then(true).otherwise(false).`as`("isBookmark"),
                posting.commentCount.`as`("commentCnt"),
                posting.clickCount.`as`("clickCnt"),
                posting.likeCount,
                posting.createdAt
            )
        )
            .from(posting)
            .leftJoin(like).on(
                like.boardType.eq(BoardType.POSTING)
                    .and(like.boardId.eq(posting.id))
                    .and(like.userId.eq(userId))
            )
            .leftJoin(bookmark).on(
                bookmark.boardId.eq(posting.id)
                    .and(bookmark.userId.eq(userId))
            )
            .where(
                booleanBuilder,
                posting.isDeleted.ne(YesOrNo.Y),
                posting.userId.ne(userId)
            )
            .orderBy(posting.id.desc())
            .limit(recordsCount + 1)
            .fetch()
    }

    override fun getMyPosting(userId: Long, postingId: Long): PostingDto {
        val isMyPosting = true

        val jPAQuery = query.select(
            constructor(
                PostingDto::class.java,
                posting.id,
                posting.userId,
                posting.subject,
                posting.content,
                CaseBuilder().`when`(like.id.isNotNull).then(true).otherwise(false).`as`("isLike"),
                CaseBuilder().`when`(bookmark.id.isNotNull).then(true).otherwise(false).`as`("isBookmark"),
                Expressions.constant(isMyPosting),
                Expressions.constant(isMyPosting),
                Expressions.constant(isMyPosting),
                posting.commentCount.`as`("commentCnt"),
                posting.clickCount.`as`("clickCnt"),
                posting.likeCount,
                posting.createdAt
            )
        )
            .from(posting)
            .leftJoin(like).on(
                like.boardType.eq(BoardType.POSTING)
                    .and(like.boardId.eq(posting.id))
                    .and(like.userId.eq(userId))
            )
            .leftJoin(bookmark).on(
                bookmark.boardId.eq(posting.id)
                    .and(bookmark.userId.eq(userId))
            )
            .where(
                posting.id.eq(postingId),
                posting.userId.eq(userId)
            )

        return jPAQuery.fetchOne() ?: throw DataNotFoundException("데이터가 없습니다.")
    }

    override fun getMyPostingList(userId: Long, nextId: Long?, size: Long): List<PostingListDto> {
        val booleanBuilder = BooleanBuilder()
        val jPAQuery = query.select(
            constructor(
                PostingListDto::class.java,
                posting.id,
                posting.userId,
                posting.subject,
                posting.content,
                CaseBuilder().`when`(like.id.isNotNull).then(true).otherwise(false).`as`("isLike"),
                CaseBuilder().`when`(bookmark.id.isNotNull).then(true).otherwise(false).`as`("isBookmark"),
                posting.commentCount.`as`("commentCnt"),
                posting.clickCount.`as`("clickCnt"),
                posting.likeCount,
                posting.createdAt
            )
        )
            .from(posting)
            .leftJoin(like).on(
                like.boardType.eq(BoardType.POSTING)
                    .and(like.boardId.eq(posting.id))
                    .and(like.userId.eq(userId))
            )
            .leftJoin(bookmark).on(
                bookmark.boardId.eq(posting.id)
                    .and(bookmark.userId.eq(userId))
            )
            .where(
                booleanBuilder,
                posting.isDeleted.ne(YesOrNo.Y),
                posting.userId.eq(userId)
            )
            .orderBy(posting.id.desc())
            .limit(size)

        return jPAQuery.fetch()
    }
}
