package io.dustin.apps.board.domain.community.comment.service

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository
import io.dustin.apps.board.domain.qna.answer.model.Answer
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReadCommentService (
    private val commentRepository: CommentRepository
) {

    fun getCommentsByPosting(userId: Long, postingId: Long, size: Long, nextId: Long?) =
        commentRepository.commentListByPosting(userId, postingId, size, nextId)

    fun replyListByComment(userId: Long, commentId: Long, size: Long, nextId: Long?) =
        commentRepository.replyListByComment(userId, commentId, size, nextId)

    fun findByIdOrNull(commentId: Long): Comment? {
        return commentRepository.findByIdOrNull(commentId)
    }

    fun findByIdOrThrow(commentId: Long): Comment {
        return commentRepository.findByIdOrThrow(commentId)
    }
}
