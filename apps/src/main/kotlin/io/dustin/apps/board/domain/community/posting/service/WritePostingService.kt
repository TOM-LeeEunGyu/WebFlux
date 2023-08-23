package io.dustin.apps.board.domain.community.posting.service

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.repository.PostingRepository
import io.dustin.apps.board.domain.like.model.LikeCountService
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service("posting")
class WritePostingService (
    private val postingRepository: PostingRepository
): LikeCountService {

    /**
     * 게시물 작성
     */
    fun create(userId: Long, subject: String, content: String): Posting {
        val posting = Posting(
            userId = userId,
            subject = subject,
            content = content
        )
        postingRepository.save(posting)
        return posting
    }

    /**
     * 게시물 수정
     */
    fun updateContent(posting: Posting, subject: String, content: String) {
        subject?.let { posting.updateSubject(subject) }
        content?.let { posting.updateSubject(content) }
        postingRepository.save(posting)
    }

    /**
     * 게시물 삭제
     */
    fun delete(posting: Posting) {
        posting.delete()
    }

    /**
     * 게시물 조회수 증가
     */
    fun click(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val clickCount: Long = posting.clickCount + 1
        posting.updateClickCount(clickCount)
    }

    /**
     * 댓글 수 증가
     */
    fun commentCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val commentCount: Long = posting.commentCount + 1
        posting.updateCommentCount(commentCount)
    }

    /**
     * 댓글 수 감소
     */
    fun commentUnCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val commentCount: Long = posting.commentCount - 1
        posting.updateLikeCount(commentCount)
    }


    /**
     * 좋아요 수 증가
     */
    override fun likeCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val likeCount: Long = posting.likeCount + 1
        posting.updateLikeCount(likeCount)
    }

    /**
     * 좋아요 수 감소
     */
    override fun likeUnCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val likeCount: Long = posting.likeCount - 1
        posting.updateLikeCount(likeCount)
    }

    fun findByIdOrNull(postingId: Long): Posting? {
        return postingRepository.findByIdOrNull(postingId)
    }

    fun findByIdOrThrow(postingId: Long): Posting {
        return postingRepository.findByIdOrThrow(postingId)
    }
}
