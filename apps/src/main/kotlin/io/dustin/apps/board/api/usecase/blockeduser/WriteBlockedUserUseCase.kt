package io.dustin.apps.board.api.usecase.blockeduser

import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto
import io.dustin.apps.board.domain.blockeduser.service.WriteBlockedUserService
import org.springframework.stereotype.Service

@Service
class WriteBlockedUserUseCase (
    private val writeBlockedUserService: WriteBlockedUserService,
) {
    fun execute(fromUserId: Long, toUserId: Long): BlockedUserDto {
        val blockedUser = writeBlockedUserService.create(fromUserId, toUserId)
        return BlockedUserDto.from(blockedUser)
    }
}
