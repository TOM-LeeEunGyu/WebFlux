package io.dustin.apps.board.domain.bookmark.model.dto;

import io.dustin.apps.board.domain.bookmark.model.Bookmark;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookMarkDto (
        Long id,
        Long boardId,
        Long userId,
        LocalDateTime createdAt

) {
    public static BookMarkDto from(Bookmark bookmark) {
        return BookMarkDto.builder()
                .id(bookmark.getId())
                .boardId(bookmark.getBoardId())
                .userId(bookmark.getUserId())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}
