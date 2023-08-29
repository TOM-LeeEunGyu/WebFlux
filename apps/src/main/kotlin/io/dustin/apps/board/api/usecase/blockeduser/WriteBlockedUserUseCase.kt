package io.dustin.apps.board.api.usecase.blockeduser

import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto
import io.dustin.apps.board.domain.blockeduser.service.ReadBlockedUserService
import io.dustin.apps.board.domain.blockeduser.service.WriteBlockedUserService
import io.dustin.apps.common.exception.DuplicatedException
import io.dustin.apps.common.utils.duplicate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteBlockedUserUseCase (
    private val readBlockedUserService: ReadBlockedUserService,
    private val writeBlockedUserService: WriteBlockedUserService,
) {
    @Transactional
    fun execute(fromUserId: Long, toUserId: Long): BlockedUserDto {
        val blockedOjb = readBlockedUserService.getBlockedObj(fromUserId,toUserId)

        if(blockedOjb == null){
            val blockedUser = writeBlockedUserService.create(fromUserId, toUserId)
            return BlockedUserDto.from(blockedUser)
        }
        else{
            return throw duplicate("이미 차단한 유저입니다.")
        }
    }
}
