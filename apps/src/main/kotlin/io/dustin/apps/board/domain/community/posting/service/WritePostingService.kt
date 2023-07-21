package io.dustin.apps.board.domain.community.posting.service

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.repository.PostingRepository
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service("posting")
class WritePostingService (
    private val postingRepository: PostingRepository
) {
    fun create(userId: Long, subject: String, content: String): Posting {
        val posting = Posting(
            userId = userId,
            subject = subject,
            content = content
        )
        postingRepository.save(posting)
        return posting
    }
    fun updateContent(posting: Posting, subject: String, content: String) {
        posting.updateSubject(subject)
        posting.updateContent(content)
        postingRepository.save(posting)
    }
    fun delete(posting: Posting) {
        posting.delete()
    }
    fun click(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val clickCount: Long = posting.clickCount + 1
        posting.setClickCount(clickCount)
    }
    fun commentCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val commentCount: Long = posting.commentCount + 1
        posting.setCommentCount(commentCount)
    }
    fun commentUnCount(postingId: Long) {
        println("게시물 id : [$postingId] 댓글 수 감소")
        val posting: Posting = findByIdOrThrow(postingId)
        val commentCount: Long = posting.commentCount - 1
        posting.setLikeCount(commentCount)
    }

    fun likeCount(postingId: Long) {
        println("게시물 id : [$postingId] 좋아요 증가")
        val posting: Posting = findByIdOrThrow(postingId)
        val likeCount: Long = posting.likeCount + 1
        posting.setLikeCount(likeCount)
    }
    fun likeUnCount(postingId: Long) {
        println("게시물 id : [$postingId] 좋아요 감소")
        val posting: Posting = findByIdOrThrow(postingId)
        val likeCount: Long = posting.likeCount - 1
        posting.setLikeCount(likeCount)
    }

    fun findById(postingId: Long): Posting {
        return postingRepository.findById(postingId).orElse(null)
    }

    fun findByIdOrThrow(postingId: Long): Posting {
        return postingRepository.findById(postingId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", commentId.toString()))
            }
    }
}
