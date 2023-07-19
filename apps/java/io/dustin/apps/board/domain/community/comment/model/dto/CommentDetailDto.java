package io.dustin.apps.board.domain.community.comment.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dustin.apps.board.domain.community.comment.model.Comment;
import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentDetailDto (
        Long id,

        @JsonIgnore
        Posting posting,

        String content,

        ResponseWithScroll<List<CommentDto>> comment,

        Boolean isLike,

        Boolean isReply,

        Long userId,

        Long postingId,

        Long replyId,

        LocalDateTime createdAt
){
    public static CommentDetailDto from(Comment comment) {
        return CommentDetailDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUserId())
                .postingId(comment.getPostingId())
                .replyId(comment.getReplyId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}


