package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.common.model.ResponseWithScroll
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 게시물 상세보기 dto
 */
@Schema(description = "게시물 상세보기 관련 응답 객체")
data class PostingDetailDto(
    @Schema(description = "게시물 dto 객체")
    val postingDto: PostingDto,
    @Schema(description = "게시물 하위에 달린 댓글을 페이징처리하여 리스트 형식으로 보여줌")
    val commentList: ResponseWithScroll<List<CommentDto>>,
) {
    companion object {
        fun from(postingDto: PostingDto, commentList: ResponseWithScroll<List<CommentDto>>): PostingDetailDto {
            return PostingDetailDto(postingDto, commentList)
        }
    }
}


