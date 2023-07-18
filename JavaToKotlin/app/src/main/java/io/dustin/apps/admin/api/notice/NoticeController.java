package io.dustin.apps.admin.api.notice;

import io.dustin.apps.admin.api.usecase.notice.DeleteNoticeUseCase;
import io.dustin.apps.admin.api.usecase.notice.ModifyNoticeUseCase;
import io.dustin.apps.admin.api.usecase.notice.ReadNoticeUseCase;
import io.dustin.apps.admin.api.usecase.notice.WriteNoticeUseCase;
import io.dustin.apps.admin.domain.notice.model.dto.NoticeDto;
import io.dustin.apps.common.annotations.AuthToken;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final ReadNoticeUseCase readNoticeUseCase;
    private final WriteNoticeUseCase writeNoticeUseCase;
    private final ModifyNoticeUseCase modifyNoticeUseCase;
    private final DeleteNoticeUseCase deleteNoticeUseCase;




    @GetMapping("/all")
    public ResponseWithScroll getNoticeList(QueryPage queryPage) {
        return readNoticeUseCase.execute(queryPage);
    }

    @GetMapping("/{noticeId}")
    public NoticeDto noticeDetail(@PathVariable("noticeId") Long noticeId){ return readNoticeUseCase.noticeDetail(noticeId);}


    @PostMapping("/create")
    public NoticeDto createNotice(@RequestBody NoticeDto noticeDto) {
        /** req 데이터 검증로직 추가 필요 */
        return writeNoticeUseCase.execute(noticeDto.adminId(), noticeDto.subject(), noticeDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @PatchMapping("/{noticeId}")
    public NoticeDto modifyNotice(@PathVariable("noticeId") Long noticeId,
                                    @RequestBody NoticeDto noticeDto) {
        /** req 데이터 검증로직 추가 필요 */
        return modifyNoticeUseCase.execute(noticeId, noticeDto.adminId(), noticeDto.subject(), noticeDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{noticeId}")
    public NoticeDto deleteNotice(@PathVariable("noticeId") Long noticeId,
                                    @RequestBody NoticeDto noticeDto) {
        /** req 데이터 검증로직 추가 필요 */
        return deleteNoticeUseCase.execute(noticeId, noticeDto.adminId());
    }
}
