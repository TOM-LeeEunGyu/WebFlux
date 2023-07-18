package io.dustin.apps.board.domain.community.comment.service;

import io.dustin.apps.board.domain.community.comment.model.Comment;
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository;
import io.dustin.apps.board.domain.like.model.LikeCountService;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service("comment")
public class WriteCommentService implements LikeCountService {

    private final CommentRepository commentRepository;

    public Comment create(Long userId, Long postingId, Long replyId, String content){
        Comment comment = Comment.builder()
                .content(content)
                .postingId(postingId)
                .userId(userId)
                .replyId(replyId)
                .build();
        this.commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public void updateContent(Comment comment, String content){
        comment.updateContent(content);
    }

    @Transactional
    public void delete(Comment comment){
        comment.delete();
    }


    @Override
    public void likeCount(long commentId) { System.out.println("댓글 id : [" + commentId + "] 좋아요 수 증가");
        Comment comment = this.findByIdOrThrow(commentId);
        Long LikeCount = comment.getLikeCount() + 1;
        comment.setLikeCount(LikeCount);
    }

    @Override
    public void likeUnCount(long commentId) {
        System.out.println("댓글 id : [" + commentId + "] 좋아요 수 감소");
        Comment comment = this.findByIdOrThrow(commentId);
        Long LikeCount = comment.getLikeCount() - 1;
        comment.setLikeCount(LikeCount);
    }

    public Comment findById(long commentId) {
        Optional<Comment> optional = this.commentRepository.findById(commentId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    public Comment findByIdOrThrow(long commentId) {
        Optional<Comment> optional = this.commentRepository.findById(commentId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("""
                    id [#1]로 조회된 댓글이 없습니다.""".replace("#1", String.valueOf(commentId)));
        }
    }


}
