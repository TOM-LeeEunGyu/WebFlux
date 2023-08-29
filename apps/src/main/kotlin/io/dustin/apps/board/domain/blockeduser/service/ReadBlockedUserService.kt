package io.dustin.apps.board.domain.blockeduser.service

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import io.dustin.apps.board.domain.blockeduser.repository.BlockedUserRepository
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.stereotype.Service

@Service
class ReadBlockedUserService(
    private val blockedUserRepository: BlockedUserRepository,
) {
    /**
     * 차단 객체(단일) 가져오기
     */
    fun getBlockedObj(fromUserId: Long, toUserId: Long): BlockedUser? {
        return blockedUserRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
    }


    /**
     * 차단 객체(단일) 가져오기
     */
    fun getBlockedUser(fromUserId: Long, toUserId: Long): BlockedUser {
        val errorMessage = "조회된 정보가 없습니다."
        return blockedUserRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
            ?: notFoundEntity(errorMessage)
    }

    /**
     * 유저가 차단한 리스트를 가져온다
     */
    fun getToUserIdList(fromUserId: Long) = blockedUserRepository.findByFromUserId(fromUserId)

    /**
     * 유저가 차단한 리스트의 객체 id값을 가져온다
     */
    fun toUserIds(fromUserId: Long) = getToUserIdList(fromUserId).map(BlockedUser::toUserId)

    /**
     * 유저를 차단한 리스트를 가져온다
     */
    fun getFromUserIdList(toUserId: Long) = blockedUserRepository.findByToUserId(toUserId)

    /**
     * 유저를 차단한 리스트의 객체 id값을 가져온다
     */
    fun fromUserIds(toUserId: Long) = getFromUserIdList(toUserId).map(BlockedUser::fromUserId)

}
