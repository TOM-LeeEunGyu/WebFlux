package io.dustin.apps.board.domain.follow.repository

import io.dustin.apps.board.domain.follow.model.Follow
import io.dustin.apps.common.repository.BaseRepository

interface FollowRepository : BaseRepository<Follow, Long> {
    fun findByFollowerIdAndFollowingId(followerId: Long, followingId: Long): Follow?
    fun deleteByFollowerIdAndFollowingId(followerId: Long, followingId: Long)
    fun findByFollowingId(followingId: Long): List<Follow>
    fun findByFollowerId(followerId: Long): List<Follow>
}
