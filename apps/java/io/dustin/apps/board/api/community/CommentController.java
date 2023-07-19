package io.dustin.apps.board.api.community;

import io.dustin.apps.board.api.usecase.community.comment.DeleteCommentUseCase;
import io.dustin.apps.board.api.usecase.community.comment.ModifyCommentUseCase;
import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase;
import io.dustin.apps.board.api.usecase.community.comment.WriteCommentUseCase;
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.common.exception.BadRequestParameterException;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final ReadCommentUseCase readCommentUseCase;
    private final WriteCommentUseCase writeCommentUseCase;
    private final ModifyCommentUseCase modifyCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;

    @GetMapping("/{postingId}/all")
    public ResponseWithScroll commentListByPosting(
            @PathVariable("postingId") Long postingId,
            QueryPage queryPage) {
        return readCommentUseCase.execute(postingId, queryPage);
    }

    @GetMapping("/{commentId}")
    public ResponseWithScroll replyListByComment(@PathVariable("commentId") Long commentId, QueryPage queryPage) {
        return readCommentUseCase.replyListByComment(commentId, queryPage);
    }


    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/posting/{postingId}")
    public CommentDto createComment(@PathVariable("postingId") Long postingId,
                                    @RequestBody CommentDto commentDto) {
        if(commentDto.content() == null) {
            throw new BadRequestParameterException("댓글 내용은 필수항목입니다.");
        }
        /**
         * 해당 게시물에 댓글 수 증가로직 추가
         */
        return writeCommentUseCase.execute(commentDto.userId(), postingId, commentDto.replyId(), commentDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @PatchMapping("/{commentId}")
    public CommentDto modifyComment(@PathVariable("commentId") Long commentId,
                                    @RequestBody CommentDto commentDto) {

        if(commentDto.content() == null) {
            throw new BadRequestParameterException("댓글 내용은 필수항목입니다.");
        }

        return modifyCommentUseCase.execute(commentDto.userId(), commentId, commentDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}")
    public CommentDto deleteComment(@PathVariable("commentId") Long commentId,
                                   @RequestBody CommentDto commentDto) {
        /**
         * 해당 게시물에 댓글 수 감소로직 추가
         */

        return deleteCommentUseCase.execute(commentDto.userId(), commentDto.postingId() ,commentId);
    }
}
