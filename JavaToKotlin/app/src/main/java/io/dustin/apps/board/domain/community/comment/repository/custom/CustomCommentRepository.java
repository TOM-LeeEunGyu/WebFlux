package io.dustin.apps.board.domain.community.comment.repository.custom;

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;

import java.util.List;

public interface CustomCommentRepository {

    List<CommentDto> commentListByPosting(long userId, long postingId, int size, Long nextId);

    List<CommentDto> replyListByComment(long userId, long commentId, int size, Long nextId);

}
