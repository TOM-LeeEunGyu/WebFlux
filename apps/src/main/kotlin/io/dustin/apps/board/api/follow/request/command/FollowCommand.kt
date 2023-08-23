package io.dustin.apps.board.api.follow.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

data class FollowCommand(

    @Schema(description = "following할 유저 id")
    @field:Min(1, message = "followingId는 필수입니다. 최소값은 1입니다.")
    val followingId: Long,

    @Schema(description = "로그인한 유저 id")
    @field:Min(1, message = "followerId는 필수입니다. 최소값은 1입니다.")
    val followerId: Long,

)
