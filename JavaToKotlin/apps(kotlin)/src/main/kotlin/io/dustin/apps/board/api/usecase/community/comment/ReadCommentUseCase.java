package io.dustin.apps.board.api.usecase.community.comment;

import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService;
import io.dustin.apps.common.model.CountByPagingInfo;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.dustin.apps.common.model.ResponseWithScrollSetting.getCountByPagingInfo;

@Service
@RequiredArgsConstructor
public class ReadCommentUseCase {

    private final ReadCommentService readCommentService;

    public ResponseWithScroll<List<CommentDto>> execute(Long postingId, QueryPage queryPage) {
        /**
         * 게시물에 대한 순수 댓글 리스트만 보여줌
         */
        int realSize = queryPage.getSize();
        int querySize = realSize + 1;
        long userId = 1;
        List<CommentDto> result = readCommentService.getCommentsByPosting(userId, postingId, querySize, queryPage.getNextId());
        CountByPagingInfo<CommentDto> cbi = getCountByPagingInfo(result, realSize);

        return ResponseWithScroll.from(cbi.result(), cbi.isLast(), cbi.nextId());

    }

    public ResponseWithScroll<List<CommentDto>> replyListByComment(Long commentId, QueryPage queryPage) {
        /**
         * 대댓글 여부 판단하기
         */
        int realSize = queryPage.getSize();
        int querySize = realSize + 1;
        long userId = 1;
        List<CommentDto> result = readCommentService.replyListByComment(userId, commentId, querySize, queryPage.getNextId());
        CountByPagingInfo<CommentDto> cbi = getCountByPagingInfo(result, realSize);

        return ResponseWithScroll.from(cbi.result(), cbi.isLast(), cbi.nextId());

    }


}