package io.dustin.apps.board.domain.restriction.posting.model.dto;

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record PostingRestrictionDto (

        Long id,
        Long fromUserId,
        Long toUserId,
        Long postingId,
        LocalDateTime createdAt

) {
    public static PostingRestrictionDto from(PostingRestriction postingRestriction) {
        return PostingRestrictionDto.builder()
                .id(postingRestriction.getId())
                .fromUserId(postingRestriction.getFromUserId())
                .toUserId(postingRestriction.getToUserId())
                .createdAt(postingRestriction.getCreatedAt())
                .postingId(postingRestriction.getPostingId())
                .build();
    }
}
