package io.dustin.apps.board.domain.follow.service

import io.dustin.apps.board.domain.follow.model.Follow
import io.dustin.apps.board.domain.follow.repository.FollowRepository
import org.springframework.stereotype.Service

@Service
class WriteFollowService (
    private val followRepository: FollowRepository
) {

    fun getFollow(followerId: Long, followingId: Long): Follow =
        followRepository.findByFollowerIdAndFollowingId(followerId, followingId)

    fun create(followerId: Long, followingId: Long): Follow {
        val follow =  Follow(
            followerId = followerId,
            followingId = followingId
        )
        followRepository.save<Follow>(follow)
        return follow
    }

    fun delete(followerId: Long, followingId: Long)  {
        followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId)
    }
}
