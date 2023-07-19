package io.dustin.apps.admin.api.usecase.notice;

import io.dustin.apps.admin.domain.notice.model.Notice;
import io.dustin.apps.admin.domain.notice.model.dto.NoticeDto;
import io.dustin.apps.admin.domain.notice.service.ReadNoticeService;
import io.dustin.apps.admin.domain.notice.service.WriteNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DeleteNoticeUseCase {

    private final ReadNoticeService readNoticeService;
    private final WriteNoticeService writeNoticeService;

    @Transactional
    public NoticeDto execute(Long adminId, Long noticeId) {
        Notice notice = readNoticeService.findById(noticeId);
        if(!notice.getAdminId().equals(adminId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        writeNoticeService.delete(notice);
        return NoticeDto.from(notice);
    }


}
