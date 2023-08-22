package io.dustin.apps.board.domain.blockeduser.service

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import io.dustin.apps.board.domain.blockeduser.repository.BlockedUserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteBlockedUserService (
    private val blockedUserRepository: BlockedUserRepository,
){

    /**
     * 유저 차단
     */
    fun create(fromUserId: Long, toUserId: Long): BlockedUser {
        val blockedUser = BlockedUser(
            fromUserId = fromUserId,
            toUserId = toUserId
        )
        blockedUserRepository.save(blockedUser)
        return blockedUser
    }

    /**
     * 유저 차단 해제
     */
    fun delete(fromUserId: Long, toUserId: Long) {
        blockedUserRepository.deleteByFromUserIdAndToUserId(fromUserId, toUserId)
    }

}
