package io.dustin.apps.board.api.usecase.blockeduser

import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto
import io.dustin.apps.board.domain.blockeduser.service.ReadBlockedUserService
import io.dustin.apps.board.domain.blockeduser.service.WriteBlockedUserService
import io.dustin.apps.common.exception.DataNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DeleteBlockedUserUseCase (
    private val writeBlockedUserService: WriteBlockedUserService,
    private val readBlockedUserService: ReadBlockedUserService,
) {

    fun execute(fromUserId: Long, toUserId: Long): BlockedUserDto {
        return try {

            val blockedUser = readBlockedUserService.getBlockedUser(fromUserId, toUserId)
            writeBlockedUserService.delete(fromUserId, toUserId)
            BlockedUserDto.from(blockedUser)

        } catch (dnf: DataNotFoundException) {
            throw DataNotFoundException(dnf.message)
        } catch (rse: ResponseStatusException) {
            throw ResponseStatusException(rse.statusCode, rse.message)
        } catch (e: Exception) {
            throw RuntimeException("잘못된 요청입니다.")
        }
    }
}
