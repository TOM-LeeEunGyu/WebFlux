package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import java.time.LocalDateTime

@Builder
class PostingDetailDtoTest(
    id: Long,
    userId: Long,
    subject: String,
    content: String,
    comment: ResponseWithScroll<List<CommentDto?>?>,
    isLike: Boolean,
    isBookmark: Boolean,
    commentCnt: Long,
    clickCnt: Long,
    likeCount: Long,
    createdAt: LocalDateTime
) {
    val id: Long
    val userId: Long
    val subject: String
    val content: String
    val comment: ResponseWithScroll<List<CommentDto>>
    val isLike: Boolean
    val isBookmark: Boolean
    val commentCnt: Long
    val clickCnt: Long
    val likeCount: Long
    val createdAt: LocalDateTime

    init {
        this.posting = posting
        this.commentList = commentList
        this.id = id
        this.userId = userId
        this.subject = subject
        this.content = content
        this.comment = comment
        this.isLike = isLike
        this.isBookmark = isBookmark
        this.commentCnt = commentCnt
        this.clickCnt = clickCnt
        this.likeCount = likeCount
        this.createdAt = createdAt
    }

    companion object {
        fun from(posting: Posting, comment: ResponseWithScroll<List<CommentDto?>?>?): PostingDetailDtoTest {
            return builder()
                .id(posting.getId())
                .userId(posting.getUserId())
                .subject(posting.getSubject())
                .content(posting.getContent())
                .comment(comment)
                .clickCnt(posting.getClickCount())
                .likeCount(posting.getLikeCount())
                .createdAt(posting.createdAt)
                .build()
        }
    }
}
