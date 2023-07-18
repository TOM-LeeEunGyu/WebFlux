package io.dustin.apps.admin.domain.notice.model.dto;

import io.dustin.apps.admin.domain.notice.model.Notice;
import io.dustin.apps.common.model.IdAble;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NoticeDto (

        Long id,

        Long adminId,

        String subject,

        String content,

        Boolean isLike,

        Long clickCount,

        LocalDateTime createdAt

) implements IdAble {
    public static NoticeDto from(Notice notice) {
        return NoticeDto.builder()
                .id(notice.getId())
                .subject(notice.getSubject())
                .content(notice.getContent())
                .adminId(notice.getAdminId())
                .clickCount(notice.getClickCount())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    @Override
    public Long getId() {
        return this.id;
    }



}

