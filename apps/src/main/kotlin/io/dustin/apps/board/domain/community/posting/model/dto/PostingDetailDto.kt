package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.common.model.ResponseWithScroll

data class PostingDetailDto(

    val postingDto: PostingDto,
    val commentList: ResponseWithScroll<List<CommentDto>>,
) {
    companion object {
        fun from(postingDto: PostingDto, commentList: ResponseWithScroll<List<CommentDto>>): PostingDetailDto {
            return PostingDetailDto(postingDto, commentList)
        }
    }
}


