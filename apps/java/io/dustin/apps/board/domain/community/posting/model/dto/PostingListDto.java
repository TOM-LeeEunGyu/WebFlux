package io.dustin.apps.board.domain.community.posting.model.dto;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.common.model.IdAble;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostingListDto(
        Long id,
        Long userId,
        String subject,
        String content,
        Boolean isLike,
        Boolean isBookmark,
        Long commentCnt,
        Long clickCnt,
        Long likeCount,
        LocalDateTime createdAt

) implements IdAble {
    public static PostingListDto from(Posting posting) {
        return PostingListDto.builder()
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
