package io.dustin.apps.board.domain.community.posting.service

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.repository.PostingRepository
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.repository.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
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
        posting.updateClickCount(clickCount)
    }
    fun commentCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val commentCount: Long = posting.commentCount + 1
        posting.updateCommentCount(commentCount)
    }
    fun commentUnCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val commentCount: Long = posting.commentCount - 1
        posting.updateLikeCount(commentCount)
    }

    fun likeCount(postingId: Long) {
        val posting: Posting = findByIdOrThrow(postingId)
        val likeCount: Long = posting.likeCount + 1
        posting.updateLikeCount(likeCount)
    }
    fun likeUnCount(postingId: Long) {
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
