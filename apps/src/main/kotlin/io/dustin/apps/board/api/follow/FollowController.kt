package io.dustin.apps.board.api.follow

import io.dustin.apps.board.api.usecase.follow.DeleteFollowUseCase
import io.dustin.apps.board.api.usecase.follow.WriteFollowUseCase
import io.dustin.apps.board.domain.follow.model.dto.FollowDto
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1")
class FollowController (

    private val writeFollowUseCase: WriteFollowUseCase,
    private val deleteFollowUseCase: DeleteFollowUseCase,

    ) {

    @PostMapping("follow/{followingId}")
    fun follow(@PathVariable("followingId") followingId: Long, @RequestBody followDto: FollowDto): FollowDto {
        /**
         * {id} 에 해당하는 유저 팔로워 증가 및 로그인 유저 팔로잉 증가 로직 필요
         */
        return writeFollowUseCase.execute(followingId, followDto.followerId)
    }

    @DeleteMapping("unfollow/{followingId}")
    fun unfollow(@PathVariable("followingId") followingId: Long, @RequestBody followDto: FollowDto): FollowDto {
        /**
         * {id} 에 해당하는 유저 팔로워 감소 및 로그인 유저 팔로잉 감소 로직 필요
         */
        return deleteFollowUseCase.execute(followingId, followDto.followerId)
    }
}
