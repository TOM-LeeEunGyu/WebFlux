package io.dustin.apps.board.domain.restriction.posting.service

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.dustin.apps.board.domain.restriction.posting.repository.PostingRestrictionRepository
import org.springframework.stereotype.Service

@Service
class WritePostingRestrictionService(
    private val postingRestrictionRepository: PostingRestrictionRepository
) {

    /**
     * 유저 제한
     */
    fun create(fromUserId: Long, toUserId: Long, postingId: Long): PostingRestriction {
        val postingRestriction = PostingRestriction(
            fromUserId = fromUserId,
            toUserId =  toUserId,
            postingId = postingId,
        )
        postingRestrictionRepository.save(postingRestriction)
        return postingRestriction
    }

    /**
     * 유저 제한 해제
     */
    fun delete(fromUserId: Long, toUserId: Long, postingId: Long) {
        postingRestrictionRepository.deleteByUsers(fromUserId, toUserId, postingId)
    }
}
