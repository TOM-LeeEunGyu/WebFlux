package io.dustin.apps.admin.domain.notice.service;

import io.dustin.apps.admin.domain.notice.repository.NoticeRepository;import io.dustin.apps.board.domain.like.model.LikeCountService;
import io.dustin.apps.admin.domain.notice.model.Notice;
import io.dustin.apps.admin.domain.notice.repository.NoticeRepository;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service("notice")
@RequiredArgsConstructor
public class WriteNoticeService implements LikeCountService {

    private final NoticeRepository noticeRepository;

    public Notice create(Long adminId, String subject, String content) {
        Notice n = Notice.builder()
                .subject(subject)
                .content(content)
                .adminId(adminId)
                .build();
        return noticeRepository.save(n);
    }

    public void updateContent(Notice notice, String subject, String content) {
        notice.updateSubject(subject);
        notice.updateContent(content);
        noticeRepository.save(notice);
    }

    public void delete(Notice notice){
        notice.delete();
    }

    @Transactional
    public void click(long noticeId){
        log.info("게시물 id : [" + noticeId + "] 조회 수 증가");
        Notice notice = this.findByIdOrThrow(noticeId);
        Long count = notice.getClickCount() + 1;
        notice.setClickCount(count);
    }

    @Override
    @Transactional
    public void likeCount(long noticeId) {
        System.out.println("공지 좋아요 id : [" + noticeId + "] 카운트 하나 올림");
        Notice notice = this.findByIdOrThrow(noticeId);
        Long likeCount = notice.getLikeCount() + 1;
        notice.setLikeCount(likeCount);
    }

    @Override
    @Transactional
    public void likeUnCount(long noticeId) {
        System.out.println("공지 좋아요 id : [" + noticeId + "] 카운트 하나 내림");
        Notice notice = this.findByIdOrThrow(noticeId);
        Long likeCount = notice.getLikeCount() - 1;
        notice.setLikeCount(likeCount);
    }


    public Notice findById(long noticeId) {
        Optional<Notice> optional = this.noticeRepository.findById(noticeId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public Notice findByIdOrThrow(long noticeId) {
        Optional<Notice> optional = this.noticeRepository.findById(noticeId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("""
                    id [#1]로 조회된 게시물이 없습니다.""".replace("#1", String.valueOf(noticeId)));
        }
    }


}
