package io.dustin.apps.board.domain.community.comment.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.IdAble
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 댓글 dto
 */
@Schema(description = "댓글 관련 응답 객체")
data class CommentDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "쿼리 dsl에서 사용하기 위한 객체")
    @JsonIgnore
    val posting: Posting?,

    @Schema(description = "내용")
    val content: String,

    @Schema(description = "로그인한 유저가 댓글에 좋아요를 눌렀는지에 대한 여부 판단")
    var isLike: Boolean,

    @Schema(description = "하위에 댓글이 존재하는지에 대한 유무 판단")
    var isReply: Boolean,

    @Schema(description = "작성 유저 id")
    val userId: Long,

    @Schema(description = "댓글이 작성된 게시물의 id")
    val postingId: Long,

    @Schema(description = "대댓글인지 아니면 최상위 댓글인지 판단")
    var replyId: Long? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime
) : IdAble {

    companion object {
        fun from(comment: Comment) = with(comment) {
            CommentDto(
                id = id ?: 0L,
                posting = null,
                content = content,
                isLike = false,
                isReply = false,
                userId = userId,
                postingId = postingId,
                replyId = replyId,
                createdAt = createdAt
            )
        }
    }

    override fun id(): Long = id
}
