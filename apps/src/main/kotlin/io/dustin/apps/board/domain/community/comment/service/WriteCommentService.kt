package io.dustin.apps.board.domain.community.comment.service

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service("comment")
class WriteCommentService (
    private val commentRepository: CommentRepository
) {
    fun create(userId: Long, postingId: Long, replyId: Long?, content: String): Comment {
        val comment = Comment(
            userId = userId,
            postingId =  postingId,
            replyId = replyId,
            content = content,
        )
        commentRepository.save<Comment>(comment)
        return comment
    }

    fun updateContent(comment: Comment, content: String) {
        comment.updateContent(content)
    }

    fun delete(comment: Comment) {
        comment.delete()
    }

    fun likeCount(commentId: Long) {
        println("댓글 id : [$commentId] 좋아요 수 증가")
        val comment = findByIdOrThrow(commentId)
        val likeCount = comment.likeCount + 1
        comment.updateLikeCount(likeCount)
    }

    fun likeUnCount(commentId: Long) {
        println("댓글 id : [$commentId] 좋아요 수 감소")
        val comment = findByIdOrThrow(commentId)
        val likeCount = comment.likeCount - 1
        comment.updateLikeCount(likeCount)
    }

    fun findById(commentId: Long): Comment {
        return commentRepository.findById(commentId).orElse(null)
    }

    fun findByIdOrThrow(commentId: Long): Comment {
        return commentRepository.findById(commentId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", commentId.toString()))
            }
    }

}
