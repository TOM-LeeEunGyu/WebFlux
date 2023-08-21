package io.dustin.apps.board.api.follow

import io.dustin.apps.board.api.usecase.follow.DeleteFollowUseCase
import io.dustin.apps.board.api.usecase.follow.WriteFollowUseCase
import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.response.CommonResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
    @PostMapping("following/{followingId}")
    @Operation(
        summary = "팔로우 추가",
        description = "팔로우 추가"
    )
    fun follow(@PathVariable("followingId") followingId: Long, @RequestBody followDto: FollowDto): CommonResponse {
        /**
         * {id} 에 해당하는 유저 팔로워 증가 및 로그인 유저 팔로잉 증가 로직 필요
         */
        writeFollowUseCase.execute(followingId, followDto.followerId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("unfollow/{followingId}")
    @Operation(
        summary = "팔로우 취소",
        description = "팔로우 취소"
    )
    fun unfollow(@PathVariable("followingId") followingId: Long, @RequestBody followDto: FollowDto): CommonResponse {
        /**
         * {id} 에 해당하는 유저 팔로워 감소 및 로그인 유저 팔로잉 감소 로직 필요
         */
        deleteFollowUseCase.execute(followingId, followDto.followerId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}
