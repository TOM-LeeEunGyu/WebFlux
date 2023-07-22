package io.dustin.apps.board.domain.restriction.posting.repository

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.dustin.apps.common.repository.BaseRepository
import java.util.*

interface PostingRestrictionRepository : BaseRepository<PostingRestriction, Long> {
    fun deleteByFromUserIdAndToUserIdAndPostingId(fromUserId: Long, toUserId: Long, postingId: Long)
    fun findByFromUserIdAndToUserIdAndPostingId(
        fromUserId: Long,
        toUserId: Long,
        postingId: Long
    ): PostingRestriction?
}
