package io.dustin.apps.board.api.blockeduser

import io.dustin.apps.board.api.usecase.blockeduser.DeleteBlockedUserUseCase
import io.dustin.apps.board.api.usecase.blockeduser.WriteBlockedUserUseCase
import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1")
class BlockedUserController (

    private val writeBlockedUserUseCase: WriteBlockedUserUseCase,
    private val deleteBlockedUserUseCase: DeleteBlockedUserUseCase

) {

    @PostMapping("/block-user/{fromUserId}/block/{toUserId}")
    fun block(
        @PathVariable("fromUserId") fromUserId: Long,
        @PathVariable("toUserId") toUserId: Long
    ): BlockedUserDto {
        return writeBlockedUserUseCase.execute(fromUserId, toUserId)
    }

    @DeleteMapping("/block-user/{fromUserId}/unblock/{toUserId}")
    fun unblock(
        @PathVariable("fromUserId") fromUserId: Long,
        @PathVariable("toUserId") toUserId: Long
    ): BlockedUserDto {
        return deleteBlockedUserUseCase.execute(fromUserId, toUserId)
    }
    /**
     * 차단 리스트 가져오기(무한스크롤, querydsl)
     */
}
