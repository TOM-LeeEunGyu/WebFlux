package io.dustin.apps.board.domain.community.posting.model.dto;

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostingDetailDtoTest(
        Long id,
        Long userId,
        String subject,
        String content,
        ResponseWithScroll<List<CommentDto>> comment,
        Boolean isLike,
        Boolean isBookmark,
        Long commentCnt,
        Long clickCnt,
        Long likeCount,
        LocalDateTime createdAt

) {
    public static PostingDetailDtoTest from(Posting posting, ResponseWithScroll<List<CommentDto>> comment) {
        return PostingDetailDtoTest.builder()
                .id(posting.getId())
                .userId(posting.getUserId())
                .subject(posting.getSubject())
                .content(posting.getContent())
                .comment(comment)
                .clickCnt(posting.getClickCount())
                .likeCount(posting.getLikeCount())
                .createdAt(posting.getCreatedAt())
                .build();
    }

}
