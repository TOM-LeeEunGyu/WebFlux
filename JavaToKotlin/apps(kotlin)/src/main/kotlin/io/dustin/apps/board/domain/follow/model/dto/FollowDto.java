package io.dustin.apps.board.domain.follow.model.dto;

import io.dustin.apps.board.domain.follow.model.Follow;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FollowDto (
        Long id,
        Long followerID,
        Long followingId,

        LocalDateTime createdAt
) {
    public static FollowDto from(Follow follow) {
        return FollowDto.builder()
                .id(follow.getId())
                .followerID(follow.getFollowerId())
                .followingId(follow.getFollowingId())
                .createdAt(follow.getCreatedAt())
                .build();
    }
}
