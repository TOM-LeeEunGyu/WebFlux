package io.dustin.apps.board.domain.community.comment.repository.custom.impl
//
//import com.querydsl.core.types.dsl.CaseBuilder
//import io.dustin.apps.board.domain.community.comment.repository.custom.CustomCommentRepository
//
//@RequiredArgsConstructor
//class CustomCommentRepositoryImpl : CustomCommentRepository {
//    private val query: JPAQueryFactory? = null
//    override fun commentListByPosting(userId: Long, postingId: Long, size: Int, nextId: Long?): List<CommentDto> {
//        val self = QComment("self")
//        val jPAQuery: JPAQuery<CommentDto> = query.select(
//            constructor(
//                CommentDto::class.java,
//                comment.id,
//                posting,
//                comment.content,
//                CaseBuilder().`when`(like.id.isNotNull()).then(true).otherwise(false).`as`("isLike"),
//                CaseBuilder().`when`(self.replyId.isNotNull()).then(true).otherwise(false).`as`("isReply"),
//                comment.userId,
//                comment.postingId,
//                comment.replyId,
//                comment.createdAt
//            )
//        ).distinct()
//            .from(posting)
//            .leftJoin(comment).on(comment.postingId.eq(posting.id))
//            .leftJoin(self).on(self.replyId.eq(comment.id).and(self.replyId.isNotNull()))
//            .leftJoin(like).on(
//                like.boardType.eq(BoardType.COMMENT)
//                    .and(like.boardId.eq(comment.id))
//                    .and(like.userId.eq(userId))
//            )
//            .where(
//                posting.id.eq(postingId),
//                comment.replyId.isNull(),
//                comment.indexByCountPagination(nextId)
//            )
//            .orderBy(comment.id.desc())
//            .limit(size)
//        return jPAQuery.fetch()
//    }
//
//    override fun replyListByComment(userId: Long, commentId: Long, size: Int, nextId: Long?): List<CommentDto> {
//        val self = QComment("self")
//        val jPAQuery: JPAQuery<CommentDto> = query.select(
//            constructor(
//                CommentDto::class.java,
//                comment.id,
//                posting,
//                comment.content,
//                CaseBuilder().`when`(like.id.isNotNull()).then(true).otherwise(false).`as`("isLike"),
//                CaseBuilder().`when`(self.replyId.isNotNull()).then(true).otherwise(false).`as`("isReply"),
//                comment.userId,
//                comment.postingId,
//                comment.replyId,
//                comment.createdAt
//            )
//        )
//            .from(comment)
//            .leftJoin(self).on(self.replyId.eq(comment.id).and(self.replyId.isNotNull()))
//            .leftJoin(posting).on(comment.postingId.eq(posting.id))
//            .leftJoin(like).on(
//                like.boardType.eq(BoardType.COMMENT)
//                    .and(like.boardId.eq(comment.id))
//                    .and(like.userId.eq(userId))
//            )
//            .where(
//                comment.replyId.eq(commentId),
//                comment.isDeleted.ne(YesOrNo.Y),
//                comment.indexByCountPagination(nextId)
//            )
//            .orderBy(comment.id.desc())
//            .limit(size)
//        return jPAQuery.fetch()
//    }
//}
