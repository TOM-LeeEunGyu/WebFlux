package io.dustin.apps.board.domain.follow.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.follow.model.Follow
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 팔로우 dto
 */
@Schema(description = "팔로우 관련 응답 객체")
data class FollowDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "from 유저 id")
    val followerId: Long,

    @Schema(description = "to 유저 id")
    val followingId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(follow: Follow) = with(follow) {
            FollowDto(
                id = id ?: 0L,
                followerId = followerId,
                followingId = followingId,
                createdAt = createdAt
            )
        }
    }
}
