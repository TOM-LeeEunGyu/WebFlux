package io.dustin.apps.admin.api.usecase.notice;

import io.dustin.apps.admin.domain.notice.model.Notice;
import io.dustin.apps.admin.domain.notice.model.dto.NoticeDto;
import io.dustin.apps.admin.domain.notice.service.ReadNoticeService;
import io.dustin.apps.admin.domain.notice.service.WriteNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ModifyNoticeUseCase {

    private final ReadNoticeService readNoticeService;
    private final WriteNoticeService writeNoticeService;

    public NoticeDto execute(Long adminID, Long noticeId, String subject, String content) {
        Notice notice = readNoticeService.findById(noticeId);
        if (!notice.getAdminId().equals(adminID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        writeNoticeService.updateContent(notice, subject, content);
        return NoticeDto.from(notice);
    }


}
