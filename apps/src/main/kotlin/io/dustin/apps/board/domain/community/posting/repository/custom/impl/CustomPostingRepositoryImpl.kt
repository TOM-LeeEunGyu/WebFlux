package io.dustin.apps.board.domain.community.posting.repository.custom.impl
//
//import com.querydsl.core.BooleanBuilder
//
//@RequiredArgsConstructor
//class CustomPostingRepositoryImpl : CustomPostingRepository {
//    private val query: JPAQueryFactory? = null
//    private val readBlockedUserService: ReadBlockedUserService? = null
//    private val readFollowService: ReadFollowService? = null
//    override fun getPosting(userId: Long, postingId: Long): PostingDto {
//        /***
//         * 게시물에 댓글을 허용하는 경우의 수는 세가지
//         * 1. 게시물 작성자의 팔로잉 리스트에 포함
//         * 2. 게시물 작성자의 팔로워 리스트에 포함
//         * 3. 게시물 작성자의 팔로잉 리스트 + 팔로워 리스트
//         * 각각을의 값을 true, false 주고 설정에 따라 true 표시된 유저들만 댓글 작성할 수 있음
//         */
//        val selectedPosting: Posting = query.selectFrom(posting).where(posting.id.eq(postingId)).fetchOne()
//        val postingAuthorId: Long = selectedPosting.userId
//        /** 게시물 작성자의 팔로잉 리스트를 가져온다  */
//        val followingIds: List<Long> = readFollowService.followingIds(postingAuthorId)
//        val isFollowing = followingIds.contains(userId)
//        /** 게시물 작성자의 팔로워 리스트를 가져온다  */
//        val followerIds: List<Long> = readFollowService.followerIds(postingAuthorId)
//        val isFollower = followerIds.contains(userId)
//        /** 게시물 작성자의 팔로잉 리스트 + 팔로워 리스트를 가져온다  */
//        val isFollowingAndFollower = if (isFollowing || isFollower) true else false
//        val jPAQuery: JPAQuery<PostingDto> = query.select(
//            constructor(
//                PostingDto::class.java,
//                posting.id,
//                posting.userId,
//                posting.subject,
//                posting.content,
//                CaseBuilder().`when`(like.id.isNotNull()).then(true).otherwise(false).`as`("isLike"),
//                CaseBuilder().`when`(bookmark.id.isNotNull()).then(true).otherwise(false).`as`("isBookmark"),
//                Expressions.constant(isFollowing),
//                Expressions.constant(isFollower),
//                Expressions.constant(isFollowingAndFollower),
//                posting.commentCount.`as`("commentCnt"),
//                posting.clickCount.`as`("clickCnt"),
//                posting.likeCount,
//                posting.createdAt
//            )
//        )
//            .from(posting)
//            .leftJoin(like).on(
//                like.boardType.eq(BoardType.POSTING)
//                    .and(like.boardId.eq(posting.id))
//                    .and(like.userId.eq(userId))
//            )
//            .leftJoin(bookmark).on(
//                bookmark.boardId.eq(posting.id)
//                    .and(bookmark.userId.eq(userId))
//            )
//            .where(
//                posting.id.eq(postingId)
//            )
//        return jPAQuery.fetchOne()
//    }
//
//    override fun getPostingList(userId: Long, nextId: Long?, size: Int): List<PostingListDto?> {
//        val booleanBuilder = BooleanBuilder()
//        if (nextId != null) {
//            booleanBuilder.and(posting.id.lt(nextId))
//        }
//        /** userID가 차단한 리스트를 가져온다 */
//        val toUserIds: List<Long> = readBlockedUserService.toUserIds(userId)
//        println("toUserIds$toUserIds")
//        /** userID를 차단한 리스트를 가져온다 */
//        val fromUserIds: List<Long> = readBlockedUserService.fromUserIds(userId)
//        println("fromUserIds$fromUserIds")
//        booleanBuilder.and(posting.userId.notIn(toUserIds).and(posting.userId.notIn(fromUserIds)))
//        val jPAQuery: JPAQuery<PostingListDto> = query.select(
//            constructor(
//                PostingListDto::class.java,
//                posting.id,
//                posting.userId,
//                posting.subject,
//                posting.content,
//                CaseBuilder().`when`(like.id.isNotNull()).then(true).otherwise(false).`as`("isLike"),
//                CaseBuilder().`when`(bookmark.id.isNotNull()).then(true).otherwise(false).`as`("isBookmark"),
//                posting.commentCount.`as`("commentCnt"),
//                posting.clickCount.`as`("clickCnt"),
//                posting.likeCount,
//                posting.createdAt
//            )
//        )
//            .from(posting)
//            .leftJoin(like).on(
//                like.boardType.eq(BoardType.POSTING)
//                    .and(like.boardId.eq(posting.id))
//                    .and(like.userId.eq(userId))
//            )
//            .leftJoin(bookmark).on(
//                bookmark.boardId.eq(posting.id)
//                    .and(bookmark.userId.eq(userId))
//            )
//            .where(
//                booleanBuilder,
//                posting.isDeleted.ne(YesOrNo.Y),
//                /** 내 게시물은 가져오지 않는다  */
//                posting.userId.ne(userId)
//            )
//            .orderBy(posting.id.desc())
//            .limit(size)
//        return jPAQuery.fetch()
//    }
//
//    override fun getMyPosting(userId: Long, postingId: Long): PostingDto {
//        /***
//         * 게시물에 댓글을 허용하는 경우의 수는 세가지
//         * 1. 게시물 작성자의 팔로잉 리스트에 포함
//         * 2. 게시물 작성자의 팔로워 리스트에 포함
//         * 3. 게시물 작성자의 팔로잉 리스트 + 팔로워 리스트
//         * 4. 각각을의 값을 true, false 주고 설정에 따라 true 표시된 유저들만 댓글 작성할 수 있음
//         * 5. 내 게시물은 전부 true
//         */
//        val isMyPosting = true
//        val jPAQuery: JPAQuery<PostingDto> = query.select(
//            constructor(
//                PostingDto::class.java,
//                posting.id,
//                posting.userId,
//                posting.subject,
//                posting.content,
//                CaseBuilder().`when`(like.id.isNotNull()).then(true).otherwise(false).`as`("isLike"),
//                CaseBuilder().`when`(bookmark.id.isNotNull()).then(true).otherwise(false).`as`("isBookmark"),
//                Expressions.constant(isMyPosting),
//                Expressions.constant(isMyPosting),
//                Expressions.constant(isMyPosting),
//                posting.commentCount.`as`("commentCnt"),
//                posting.clickCount.`as`("clickCnt"),
//                posting.likeCount,
//                posting.createdAt
//            )
//        )
//            .from(posting)
//            .leftJoin(like).on(
//                like.boardType.eq(BoardType.POSTING)
//                    .and(like.boardId.eq(posting.id))
//                    .and(like.userId.eq(userId))
//            )
//            .leftJoin(bookmark).on(
//                bookmark.boardId.eq(posting.id)
//                    .and(bookmark.userId.eq(userId))
//            )
//            .where(
//                posting.id.eq(postingId),
//                posting.userId.eq(userId)
//            )
//        return jPAQuery.fetchOne()
//    }
//
//    override fun getMyPostingList(userId: Long, nextId: Long?, size: Int): List<PostingListDto?> {
//        val booleanBuilder = BooleanBuilder()
//        if (nextId != null) {
//            booleanBuilder.and(posting.id.lt(nextId))
//        }
//        val jPAQuery: JPAQuery<PostingListDto> = query.select(
//            constructor(
//                PostingListDto::class.java,
//                posting.id,
//                posting.userId,
//                posting.subject,
//                posting.content,
//                CaseBuilder().`when`(like.id.isNotNull()).then(true).otherwise(false).`as`("isLike"),
//                CaseBuilder().`when`(bookmark.id.isNotNull()).then(true).otherwise(false).`as`("isBookmark"),
//                posting.commentCount.`as`("commentCnt"),
//                posting.clickCount.`as`("clickCnt"),
//                posting.likeCount,
//                posting.createdAt
//            )
//        )
//            .from(posting)
//            .leftJoin(like).on(
//                like.boardType.eq(BoardType.POSTING)
//                    .and(like.boardId.eq(posting.id))
//                    .and(like.userId.eq(userId))
//            )
//            .leftJoin(bookmark).on(
//                bookmark.boardId.eq(posting.id)
//                    .and(bookmark.userId.eq(userId))
//            )
//            .where(
//                booleanBuilder,
//                posting.isDeleted.ne(YesOrNo.Y),
//                posting.userId.eq(userId)
//            )
//            .orderBy(posting.id.desc())
//            .limit(size)
//        return jPAQuery.fetch()
//    }
//}
