package io.dustin.apps.board.domain.blockeduser.repository

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import io.dustin.apps.common.repository.BaseRepository

interface BlockedUserRepository: BaseRepository<BlockedUser, Long> {
    fun deleteByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long)
    fun findByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long): BlockedUser?
    fun findByFromUserId(fromUserId: Long): List<BlockedUser>
    fun findByToUserId(toUserId: Long): List<BlockedUser>

}
