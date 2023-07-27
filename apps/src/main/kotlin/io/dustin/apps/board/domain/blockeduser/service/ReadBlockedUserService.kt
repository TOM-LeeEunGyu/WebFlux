package io.dustin.apps.board.domain.blockeduser.service

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import io.dustin.apps.board.domain.blockeduser.repository.BlockedUserRepository
import io.dustin.apps.common.utils.dataNotFound
import org.springframework.stereotype.Service

@Service
class ReadBlockedUserService(
    private val blockedUserRepository: BlockedUserRepository,
) {
    fun getBlockedUser(fromUserId: Long, toUserId: Long) = blockedUserRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
            ?: dataNotFound("없음")

    fun getToUserIdList(fromUserId: Long) = blockedUserRepository.findByFromUserId(fromUserId)

    fun toUserIds(fromUserId: Long) = getToUserIdList(fromUserId).map(BlockedUser::toUserId)

    fun getFromUserIdList(toUserId: Long) = blockedUserRepository.findByToUserId(toUserId)

    fun fromUserIds(toUserId: Long) = getFromUserIdList(toUserId).map(BlockedUser::fromUserId)

}
