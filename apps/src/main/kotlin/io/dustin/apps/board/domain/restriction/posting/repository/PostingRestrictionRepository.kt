package io.dustin.apps.board.domain.restriction.posting.repository

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.dustin.apps.common.repository.BaseRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface PostingRestrictionRepository : BaseRepository<PostingRestriction, Long> {
    @Query("SELECT s FROM PostingRestriction s WHERE s.fromUserId = :fromUserId AND s.toUserId = :toUserId AND s.postingId = :postingId")
    fun findByUsers(
        fromUserId: Long,
        toUserId: Long,
        postingId: Long
    ): PostingRestriction?

    @Query("DELETE FROM PostingRestriction s WHERE s.fromUserId = :fromUserId AND s.toUserId = :toUserId AND s.postingId = :postingId")
    fun deleteByUsers(
        fromUserId: Long,
        toUserId: Long,
        postingId: Long
    )

}


