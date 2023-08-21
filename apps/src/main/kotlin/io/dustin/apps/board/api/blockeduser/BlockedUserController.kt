package io.dustin.apps.board.api.blockeduser

import io.dustin.apps.board.api.usecase.blockeduser.DeleteBlockedUserUseCase
import io.dustin.apps.board.api.usecase.blockeduser.WriteBlockedUserUseCase
import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/v1")
@Tag(name = "유저 차단 관련 API", description = "유저 차단  관련 API를 제공한다.")
class BlockedUserController (

    private val writeBlockedUserUseCase: WriteBlockedUserUseCase,
    private val deleteBlockedUserUseCase: DeleteBlockedUserUseCase

) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/block-user/{fromUserId}/block/{toUserId}")
    @Operation(
        summary = "유저 차단",
        description = "유저 차단"
    )
    fun block(
        @PathVariable("fromUserId") fromUserId: Long,
        @PathVariable("toUserId") toUserId: Long
    ): CommonResponse {
        writeBlockedUserUseCase.execute(fromUserId, toUserId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/block-user/{fromUserId}/unblock/{toUserId}")
    @Operation(
        summary = "유저 차단 해제",
        description = "유저 차단 해제"
    )
    fun unblock(
        @PathVariable("fromUserId") fromUserId: Long,
        @PathVariable("toUserId") toUserId: Long
    ): CommonResponse {
        deleteBlockedUserUseCase.execute(fromUserId, toUserId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    /**
     * 차단 리스트 가져오기(무한스크롤, querydsl)
     * todo
     */
}
