package io.dustin.apps.board.domain.community.posting.model.dto

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.common.model.ResponseWithScroll

data class PostingDetailDto(
    val posting: PostingDto,
    val commentList: ResponseWithScroll<List<CommentDto>>,


    companion object {

        fun from(postingDetailDto: PostingDetailDto) = with(postingDetailDto) {



    }
}
