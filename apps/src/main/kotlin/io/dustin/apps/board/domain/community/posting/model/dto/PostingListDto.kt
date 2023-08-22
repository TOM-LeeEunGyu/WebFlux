package io.dustin.apps.board.domain.community.posting.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.BaseEntity
import io.dustin.apps.common.model.IdAble
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 게시물 리스트 dto
 */
@Schema(description = "게시물 리스트 관련 응답 객체")
data class PostingListDto(
    @Schema(description = "id")
    val id: Long,

    @Schema(description = "유저 id")
    val userId: Long,

    @Schema(description = "제목")
    val subject: String,

    @Schema(description = "내용")
    val content: String,

    @Schema(description = "로그인한 유저가 해당 게시물에 좋아요를 눌렀는지에 대한 유무 판단")
    var isLike: Boolean,

    @Schema(description = "로그인한 유저가 해당 게시물을 북마크에 추가했는지에 대한 유무 판단")
    var isBookmark: Boolean,

    @Schema(description = "댓글수")
    var commentCnt: Long,

    @Schema(description = "조회수")
    var clickCnt: Long,

    @Schema(description = "좋아요 수")
    var likeCount: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime? = LocalDateTime.now(),
) : IdAble {
    companion object {
        fun from(posting: Posting) =  with(posting) {
            PostingListDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                isLike = false, // Set default value for isLike
                isBookmark = false, // Set default value for isBookmark
                commentCnt = 0, // Set default value for commentCnt
                clickCnt = 0, // Set default value for clickCnt
                likeCount = 0, // Set default value for likeCount
                createdAt = createdAt,

            )
        }
    }

    override fun id(): Long = id
}
