package io.dustin.apps.board.domain.community.comment.service;

import io.dustin.apps.board.domain.community.comment.model.Comment;
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * cqrs -> srp
 */
@Service
@RequiredArgsConstructor
public class ReadCommentService {

    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByPosting(long userId, long postingId, int size, Long nextId) {
        return commentRepository.commentListByPosting(userId, postingId, size, nextId);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> replyListByComment(long userId, long commentId, int size, Long nextId) {
        return commentRepository.replyListByComment(userId, commentId, size, nextId);
    }


    @Transactional(readOnly = true)
    public Comment findById(long commentId) {
        Optional<Comment> optional = this.commentRepository.findById(commentId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
    @Transactional(readOnly = true)
    public Comment findByIdOrThrow(long commentId) {
        Optional<Comment> optional = this.commentRepository.findById(commentId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw new DataNotFoundException("""
                    id [#1]로 조회된 게시물이 없습니다.""".replace("#1", String.valueOf(commentId)));
        }
    }

}
