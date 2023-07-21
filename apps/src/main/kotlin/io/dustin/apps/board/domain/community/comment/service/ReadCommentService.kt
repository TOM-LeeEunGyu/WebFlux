package io.dustin.apps.board.domain.community.comment.service

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReadCommentService (
    private val commentRepository: CommentRepository
) {

    @Transactional(readOnly = true)
    fun getCommentsByPosting(userId: Long, postingId: Long, size: Int, nextId: Long?) =
        commentRepository.commentListByPosting(userId, postingId, size, nextId)

    @Transactional(readOnly = true)
    fun replyListByComment(userId: Long, commentId: Long, size: Int, nextId: Long?) =
        commentRepository.replyListByComment(userId, commentId, size, nextId)

    fun findById(commentId: Long): Comment? {
        return commentRepository.findById(commentId).orElse(null)
    }

    fun findByIdOrThrow(commentId: Long): Comment {
        return commentRepository.findById(commentId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", commentId.toString()))
            }
    }
}
