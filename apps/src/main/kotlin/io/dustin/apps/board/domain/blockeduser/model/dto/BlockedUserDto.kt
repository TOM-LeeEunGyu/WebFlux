package io.dustin.apps.board.domain.blockeduser.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.blockeduser.model.BlockedUser
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 차단유저 dto
 */
@Schema(description = "차단유저 관련 응답 객체")
data class BlockedUserDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "from 유저 id 값")
    val fromUserId: Long,

    @Schema(description = "to 유저 id 값")
    val toUserId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "거래일시", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(blockedUser: BlockedUser) = with(blockedUser) {
            BlockedUserDto(
                id = id ?: 0L,
                fromUserId = fromUserId,
                toUserId = toUserId,
                createdAt = createdAt
            )
        }
    }
}
