package io.dustin.apps.board.domain.blockeduser.service

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import io.dustin.apps.board.domain.blockeduser.repository.BlockedUserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteBlockedUserService (
    private val blockedUserRepository: BlockedUserRepository,
){

    fun create(fromUserId: Long, toUserId: Long): BlockedUser {
        val blockedUser = BlockedUser(
            fromUserId = fromUserId,
            toUserId = toUserId
        )
        blockedUserRepository.save<BlockedUser>(blockedUser)
        return blockedUser
    }

    fun delete(fromUserId: Long, toUserId: Long) {
        blockedUserRepository.deleteByFromUserIdAndToUserId(fromUserId, toUserId)
    }

}
