package io.dustin.apps.admin.api.usecase.notice;

import io.dustin.apps.admin.domain.notice.model.Notice;
import io.dustin.apps.admin.domain.notice.model.dto.NoticeDto;
import io.dustin.apps.admin.domain.notice.service.ReadNoticeService;
import io.dustin.apps.admin.domain.notice.service.WriteNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteNoticeUseCase {

    private final ReadNoticeService readNoticeService;
    private final WriteNoticeService writeNoticeService;

    public NoticeDto execute(Long adminId, String subject, String content) {
        Notice notice = writeNoticeService.create(adminId, subject, content);
        NoticeDto dto = NoticeDto.from(notice);
        return NoticeDto.from(notice);
    }
}
