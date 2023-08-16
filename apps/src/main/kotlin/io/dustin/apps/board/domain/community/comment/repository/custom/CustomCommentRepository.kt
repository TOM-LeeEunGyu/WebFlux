package io.dustin.apps.board.domain.community.comment.repository.custom

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto

interface CustomCommentRepository {
    fun commentListByPosting(userId: Long, postingId: Long, recordsCount: Long, nextId: Long?): List<CommentDto>
    fun replyListByComment(userId: Long, commentId: Long, recordsCount: Long, nextId: Long?): List<CommentDto>
}
