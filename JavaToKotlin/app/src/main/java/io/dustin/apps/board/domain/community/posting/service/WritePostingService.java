package io.dustin.apps.board.domain.community.posting.service;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.board.domain.community.posting.repository.PostingRepository;
import io.dustin.apps.board.domain.like.model.LikeCountService;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service("posting")
@RequiredArgsConstructor
public class WritePostingService implements LikeCountService {

    private final PostingRepository postingRepository;

    public Posting create(Long userId, String subject, String content) {
        Posting q = Posting.builder()
                .userId(userId)
                .subject(subject)
                .content(content)
                .build();
        return postingRepository.save(q);
    }


    @Transactional
    public void updateContent(Posting posting, String subject, String content) {
        posting.updateSubject(subject);
        posting.updateContent(content);
        postingRepository.save(posting);
    }

    @Transactional
    public void delete(Posting posting){
        posting.delete();
    }


    @Transactional
    public void click(long postingId){
        log.info("게시물 id : [" + postingId + "] 조회 수 증가");
        Posting posting = this.findByIdOrThrow(postingId);
        Long count = posting.getClickCount() + 1;
        posting.setClickCount(count);
    }

    @Transactional
    public void commentCount(long postingId) {
        Posting posting = this.findByIdOrThrow(postingId);
        Long count = posting.getCommentCount() + 1;
        log.info("게시물 id : [{}] 댓글 수 증가", posting.getCommentCount());
        posting.setCommentCount(count);
    }

    @Transactional
    public void commentUnCount(long postingId) {
        System.out.println("게시물 id : [" + postingId + "] 댓글 수 감소");
        Posting posting = this.findByIdOrThrow(postingId);
        Long count = posting.getCommentCount() - 1;
        posting.setLikeCount(count);
    }

    @Override
    @Transactional
    public void likeCount(long postingId) { System.out.println("게시물 id : [" + postingId + "] 좋아요 증가");
        Posting posting = this.findByIdOrThrow(postingId);
        Long likeCount = posting.getLikeCount() + 1;
        posting.setLikeCount(likeCount);
    }

    @Override
    @Transactional
    public void likeUnCount(long postingId) {
        System.out.println("게시물 id : [" + postingId + "] 좋아요 감소");
        Posting posting = this.findByIdOrThrow(postingId);
        Long likeCount = posting.getLikeCount() - 1;
        posting.setLikeCount(likeCount);
    }

    public Posting findById(long postingId) {
        Optional<Posting> optional = this.postingRepository.findById(postingId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public Posting findByIdOrThrow(long postingId) {
        Optional<Posting> optional = this.postingRepository.findById(postingId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("""
                    id [#1]로 조회된 게시물이 없습니다.""".replace("#1", String.valueOf(postingId)));
        }
    }
}
