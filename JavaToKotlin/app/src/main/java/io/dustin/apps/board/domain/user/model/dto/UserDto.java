package io.dustin.apps.board.domain.user.model.dto;

import io.dustin.apps.board.domain.user.model.SiteUser;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto (
        Long id,

        String nickName,

        String password,

        String email,

        String profile,

        LocalDateTime createdAt
) {
    public static UserDto from(SiteUser siteUser) {
        return UserDto.builder()
                .id(siteUser.getId())
                .nickName(siteUser.getNickName())
                .password(siteUser.getPassword())
                .profile(siteUser.getProfile())
                .createdAt(siteUser.getCreatedAt())
                .build();
    }
}


