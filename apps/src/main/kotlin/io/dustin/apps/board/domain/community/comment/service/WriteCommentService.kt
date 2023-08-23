package io.dustin.apps.board.domain.community.comment.service

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository
import io.dustin.apps.board.domain.like.model.LikeCountService
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service("comment")
class WriteCommentService (
    private val commentRepository: CommentRepository
): LikeCountService {

    /**
     * 댓글 작성
     */
    fun create(userId: Long, postingId: Long, replyId: Long?, content: String): Comment {
        val comment = Comment(
            userId = userId,
            postingId =  postingId,
            replyId = replyId,
            content = content,
        )
        commentRepository.save(comment)
        return comment
    }

    /**
     * 댓글 수정
     */
    fun updateContent(comment: Comment, content: String) {
        comment.updateContent(content)
    }

    /**
     * 댓글 삭제
     */
    fun delete(comment: Comment) {
        comment.delete()
    }

    /**
     * 댓글 좋아요 수 증가
     */
    override fun likeCount(commentId: Long) {
        println("댓글 id : [$commentId] 좋아요 수 증가")
        val comment = findByIdOrThrow(commentId)
        val likeCount = comment.likeCount + 1
        comment.updateLikeCount(likeCount)
    }

    /**
     * 댓글 좋아요 수 감소
     */
     override fun likeUnCount(commentId: Long) {
        println("댓글 id : [$commentId] 좋아요 수 감소")
        val comment = findByIdOrThrow(commentId)
        val likeCount = comment.likeCount - 1
        comment.updateLikeCount(likeCount)
    }

    fun findByIdOrNull(commentId: Long): Comment? {
        return commentRepository.findByIdOrNull(commentId)
    }

    fun findByIdOrThrow(commentId: Long): Comment {
        return commentRepository.findByIdOrThrow(commentId)
    }

}
