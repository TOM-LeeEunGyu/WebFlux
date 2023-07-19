package io.dustin.apps.board.domain.like.model.dto;

import io.dustin.apps.board.domain.like.model.Like;
import io.dustin.apps.common.code.BoardType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LikeDto (
        Long id,
        Long boardId,
        Long userId,
        BoardType boardType,
        LocalDateTime createdAt
) {
    public static LikeDto from(Like like) {
        return LikeDto.builder()
                .id(like.getId())
                .boardId(like.getBoardId())
                .userId(like.getUserId())
                .boardType(like.getBoardType())
                .createdAt(like.getCreatedAt())
                .build();
    }


}
