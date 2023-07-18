package io.dustin.apps.board.domain.community.posting.model.dto;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.common.model.IdAble;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostingDto (
        Long id,
        Long userId,
        String subject,
        String content,
        Boolean isLike,
        Boolean isBookmark,
        Boolean following,
        Boolean follower,
        Boolean followingAndFollower,
        Long commentCnt,
        Long clickCnt,
        Long likeCount,
        LocalDateTime createdAt

) implements IdAble {
    public static PostingDto from(Posting posting) {
        return PostingDto.builder()
                .id(posting.getId())
                .userId(posting.getUserId())
                .subject(posting.getSubject())
                .content(posting.getContent())
                .createdAt(posting.getCreatedAt())
                .build();
    }

    @Override
    public Long getId() {
        return this.id;
    }

}
