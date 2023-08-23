package io.dustin.apps.board.api.follow

import io.dustin.apps.board.api.follow.request.command.FollowCommand
import io.dustin.apps.board.api.usecase.follow.DeleteFollowUseCase
import io.dustin.apps.board.api.usecase.follow.WriteFollowUseCase
import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/v1/follow")
@Tag(name = "팔로우 관련 API", description = "팔로우 관련 API를 제공한다.")
class FollowController (

    private val writeFollowUseCase: WriteFollowUseCase,
    private val deleteFollowUseCase: DeleteFollowUseCase,

    ) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("following")
    @Operation(
        summary = "팔로우 추가",
        description = "팔로우 추가"
    )
    fun follow(@RequestBody @Valid command: FollowCommand): CommonResponse {

        writeFollowUseCase.execute(command.followingId, command.followerId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("unfollow")
    @Operation(
        summary = "팔로우 취소",
        description = "팔로우 취소"
    )
    fun unfollow(@RequestBody @Valid command: FollowCommand): CommonResponse {

        deleteFollowUseCase.execute(command.followingId, command.followerId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}
