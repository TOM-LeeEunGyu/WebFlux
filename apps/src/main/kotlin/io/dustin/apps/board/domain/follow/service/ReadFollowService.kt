package io.dustin.apps.board.domain.follow.service

import io.dustin.apps.board.domain.follow.model.Follow
import io.dustin.apps.board.domain.follow.repository.FollowRepository
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
class ReadFollowService (
    private val followRepository: FollowRepository
) {
    fun getFollowingIdList(followerId: Long): List<Follow> =
        followRepository.findByFollowerId(followerId)

    fun followingIds(followerId: Long): List<Long> = getFollowingIdList(followerId).map { it.followingId }

    fun getFollowerIdList(followingId: Long): List<Follow> =
        followRepository.findByFollowingId(followingId)

    fun followerIds(followingId: Long): List<Long> = getFollowerIdList(followingId).map { it.followerId }

}
