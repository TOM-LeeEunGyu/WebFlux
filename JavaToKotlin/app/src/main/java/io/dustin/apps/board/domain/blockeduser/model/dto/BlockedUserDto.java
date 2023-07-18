package io.dustin.apps.board.domain.blockeduser.model.dto;

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser;
import io.dustin.apps.board.domain.bookmark.model.Bookmark;
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BlockedUserDto (
        Long id,
        Long fromUserId,
        Long toUserId,
        LocalDateTime createdAt
) {
    public static BlockedUserDto from(BlockedUser blockedUser) {
        return BlockedUserDto.builder()
                .id(blockedUser.getId())
                .fromUserId(blockedUser.getFromUserId())
                .toUserId(blockedUser.getToUserId())
                .createdAt(blockedUser.getCreatedAt())
                .build();
    }
}
