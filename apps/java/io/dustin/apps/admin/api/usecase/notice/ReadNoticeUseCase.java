package io.dustin.apps.admin.api.usecase.notice;


import io.dustin.apps.admin.domain.notice.model.dto.NoticeDto;
import io.dustin.apps.admin.domain.notice.service.ReadNoticeService;
import io.dustin.apps.admin.domain.notice.service.WriteNoticeService;
import io.dustin.apps.common.model.CountByPagingInfo;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.dustin.apps.common.model.ResponseWithScrollSetting.getCountByPagingInfo;

@Service
@RequiredArgsConstructor
public class ReadNoticeUseCase {

    private final ReadNoticeService readNoticeService;
    private final WriteNoticeService writeNoticeService;

    public ResponseWithScroll<List<NoticeDto>> execute(QueryPage queryPage) {
        /**
         * 게시물 리스트 보여줌
         */
        long userId = 1;
        int realSize = queryPage.getSize();
        int querySize = realSize + 1;

        List<NoticeDto> result = readNoticeService.getNoticeList(userId, queryPage.getNextId(), querySize);
        CountByPagingInfo<NoticeDto> cbi = getCountByPagingInfo(result, realSize);

        return ResponseWithScroll.from(cbi.result(), cbi.isLast(), cbi.nextId());
    }

    @Transactional
    public NoticeDto noticeDetail(Long noticeId) {
        long userId = 1;
        NoticeDto notice = readNoticeService.getNotice(userId, noticeId);
        writeNoticeService.click(noticeId);
        return notice;


    }



}
