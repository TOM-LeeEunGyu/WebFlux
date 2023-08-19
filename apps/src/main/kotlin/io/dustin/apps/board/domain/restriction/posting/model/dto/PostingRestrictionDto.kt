package io.dustin.apps.board.domain.restriction.posting.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 게시물 제한 dto
 */
@Schema(description = "게시물 제한 관련 응답 객체")
data class PostingRestrictionDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "from 유저 id 값")
    val fromUserId: Long,

    @Schema(description = "to 유저 id 값")
    val toUserId: Long,

    @Schema(description = "제한할 게시물 아이디")
    val postingId: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(postingRestriction: PostingRestriction) = with(postingRestriction) {
            PostingRestrictionDto(
                id = id ?: 0L,
                fromUserId = fromUserId,
                toUserId = toUserId,
                postingId = postingId,
                createdAt = createdAt
            )
        }
    }
}
