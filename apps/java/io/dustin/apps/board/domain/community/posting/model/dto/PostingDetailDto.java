package io.dustin.apps.board.domain.community.posting.model.dto;

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.Builder;

import java.util.List;

@Builder
public record PostingDetailDto (
        PostingDto posting,

        ResponseWithScroll<List<CommentDto>> commentList

) {
    public static PostingDetailDto from(PostingDto posting,  ResponseWithScroll<List<CommentDto>> commentList) {
        return PostingDetailDto.builder()
                .posting(posting)
                .commentList(commentList)
                .build();
    }

}
