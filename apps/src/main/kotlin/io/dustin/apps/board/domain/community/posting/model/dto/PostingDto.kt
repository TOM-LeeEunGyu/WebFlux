package io.dustin.apps.board.domain.community.posting.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.common.model.IdAble
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.time.LocalDateTime.now

/**
 * 게시게 dto
 */
@Schema(description = "게시물 관련 응답 객체")
data class PostingDto(
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

    @Schema(description = "해당 게시물의 로그인한 유저의 팔로잉 리스트에 해당되는 유저가 작성했는지에 대한 유무 판다")
    var following: Boolean,

    @Schema(description = "해당 게시물의 로그인한 유저의 팔로워 리스트에 해당되는 유저가 작성했는지에 대한 유무 판다")
    var follower: Boolean,

    @Schema(description = "해당 게시물의 로그인한 유저의 팔로잉 및 팔로워 리스트에 해당되는 유저가 작성했는지에 대한 유무 판다")
    var followingAndFollower: Boolean,

    @Schema(description = "댓글수")
    var commentCnt: Long,

    @Schema(description = "조회수")
    var clickCnt: Long,

    @Schema(description = "좋아요 수")
    var likeCount: Long,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "객체 생성 날짜", example = "1900-01-01 23:59:59")
    val createdAt: LocalDateTime = now(),

    ): IdAble {

    companion object {
        fun from(posting: Posting) = with(posting) {
            PostingDto(
                id = id ?: 0L,
                userId = userId,
                subject = subject,
                content = content,
                isLike =  false,
                isBookmark = false,
                following = false,
                follower = false,
                followingAndFollower = false,
                commentCnt = 0,
                clickCnt = 0,
                likeCount = 0,
                createdAt = createdAt,
            )
        }
    }
    override fun id(): Long = id
}
