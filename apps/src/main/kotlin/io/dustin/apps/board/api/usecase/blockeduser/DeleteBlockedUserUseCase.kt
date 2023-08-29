package io.dustin.apps.board.api.usecase.blockeduser

import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto
import io.dustin.apps.board.domain.blockeduser.service.ReadBlockedUserService
import io.dustin.apps.board.domain.blockeduser.service.WriteBlockedUserService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.Throws

@Service
class DeleteBlockedUserUseCase (
    private val writeBlockedUserService: WriteBlockedUserService,
    private val readBlockedUserService: ReadBlockedUserService,
) {


    @Transactional
    fun execute(fromUserId: Long, toUserId: Long): BlockedUserDto {
        val blockedUser = readBlockedUserService.getBlockedUser(fromUserId, toUserId)
        writeBlockedUserService.delete(fromUserId, toUserId)
        return BlockedUserDto.from(blockedUser)
    }
}
