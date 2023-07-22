package io.dustin.apps.board.domain.restriction.posting.service

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.dustin.apps.board.domain.restriction.posting.repository.PostingRestrictionRepository
import org.springframework.stereotype.Service

@Service
class WritePostingRestrictionService(
    private val postingRestrictionRepository: PostingRestrictionRepository
) {

    fun create(fromUserId: Long, toUserId: Long, postingId: Long): PostingRestriction {
        val postingRestriction = PostingRestriction(
            fromUserId = fromUserId,
            toUserId =  toUserId,
            postingId = postingId,
        )
        postingRestrictionRepository.save<PostingRestriction>(postingRestriction)
        return postingRestriction
    }

    fun delete(fromUserId: Long, toUserId: Long, postingId: Long) {
        postingRestrictionRepository.deleteByFromUserIdAndToUserIdAndPostingId(fromUserId, toUserId, postingId)
    }
}
