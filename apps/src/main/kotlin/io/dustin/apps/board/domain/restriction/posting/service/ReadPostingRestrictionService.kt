package io.dustin.apps.board.domain.restriction.posting.service

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.dustin.apps.board.domain.restriction.posting.repository.PostingRestrictionRepository
import org.springframework.stereotype.Service

@Service
class ReadPostingRestrictionService (
    private val postingRestrictionRepository: PostingRestrictionRepository
) {
    fun getPostingRestrictionUser(fromUserId: Long, toUserId: Long, postingId: Long): PostingRestriction?  =
            postingRestrictionRepository.findByFromUserIdAndToUserIdAndPostingId(
                fromUserId,
                toUserId,
                postingId
            )
}
