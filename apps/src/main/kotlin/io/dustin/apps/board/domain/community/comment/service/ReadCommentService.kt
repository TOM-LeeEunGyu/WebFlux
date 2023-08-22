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

    /**
     * 게시물에 대한 댓글 리스트 불러오기
     */
    fun getCommentsByPosting(userId: Long, postingId: Long, recordsCount: Long, nextId: Long?) =
        commentRepository.commentListByPosting(userId, postingId, recordsCount, nextId)

    /**
     * 하위댓글 리스트 불러오기
     */
    fun replyListByComment(userId: Long, commentId: Long, recordsCount: Long, nextId: Long?) =
        commentRepository.replyListByComment(userId, commentId, recordsCount, nextId)

    fun findByIdOrNull(commentId: Long): Comment? {
        return commentRepository.findByIdOrNull(commentId)
    }

    fun findByIdOrThrow(commentId: Long): Comment {
        return commentRepository.findByIdOrThrow(commentId)
    }
}
