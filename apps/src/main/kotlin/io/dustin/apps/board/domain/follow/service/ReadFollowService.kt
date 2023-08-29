package io.dustin.apps.board.domain.follow.service

import io.dustin.apps.board.domain.follow.model.Follow
import io.dustin.apps.board.domain.follow.repository.FollowRepository
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
class ReadFollowService (
    private val followRepository: FollowRepository
) {

    /**
     * 팔로우 객체(단일)을 가져온다
     */
    fun getFollowObj(followerId: Long, followingId: Long): Follow? {
        return followRepository.findByFollowerIdAndFollowingId(followerId, followingId)
    }

    /**
     * 팔로우 객체(단일)을 가져온다
     */
    fun getFollow(followerId: Long, followingId: Long): Follow {
        val errorMessage = "조회된 정보가 없습니다."
        return followRepository.findByFollowerIdAndFollowingId(followerId, followingId) ?: notFoundEntity(errorMessage)
    }

    /**
     * 유저의 팔로잉리스트(팔로우객체)를 가져온다
     */
    fun getFollowingIdList(followerId: Long): List<Follow> =
        followRepository.findByFollowerId(followerId)

    /**
     * 유저의 팔로잉리스트(id)를 가져온다
     */
    fun followingIds(followerId: Long): List<Long> = getFollowingIdList(followerId).map { it.followingId }

    /**
     * 유저의 팔로워리스트(팔로우객체)를 가져온다
     */
    fun getFollowerIdList(followingId: Long): List<Follow> =
        followRepository.findByFollowingId(followingId)

    /**
     * 유저의 팔로워리스트(id)를 가져온다
     */
    fun followerIds(followingId: Long): List<Long> = getFollowerIdList(followingId).map { it.followerId }

}
