package io.dustin.apps.board.api.community

import io.dustin.apps.board.api.community.request.command.CommentCreateCommand
import io.dustin.apps.board.api.usecase.community.comment.DeleteCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.ModifyCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.WriteCommentUseCase
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.common.exception.BadRequestParameterException
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/comment")
class CommentController (

    private val readCommentUseCase: ReadCommentUseCase,
    private val writeCommentUseCase: WriteCommentUseCase,
    private val modifyCommentUseCase: ModifyCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,

    ) {

//    @GetMapping("/{postingId}/all")
//    fun commentListByPosting(
//        @PathVariable("postingId") postingId: Long,
//        queryPage: QueryPage
//    ): ResponseWithScroll<*> {
//        return readCommentUseCase.execute(postingId, queryPage)
//    }

    @GetMapping("/{commentId}")
    fun replyListByComment(@PathVariable("commentId") commentId: Long, queryPage: QueryPage): ResponseWithScroll<*> {
        return readCommentUseCase.replyListByComment(commentId, queryPage)
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/posting/{postingId}")
    fun createComment(
        @PathVariable("postingId") postingId: Long,
        @RequestBody @Valid command: CommentCreateCommand
    ): CommentDto {
        if (command.content == null) {
            throw BadRequestParameterException("댓글 내용은 필수항목입니다.")
        }
        /**
         * 해당 게시물에 댓글 수 증가로직 추가
         */
        return writeCommentUseCase.execute(command.userId, postingId, command.replyId, command.content)
    }

    //@PreAuthorize("isAuthenticated()")
    @PatchMapping("/{commentId}")
    fun modifyComment(
        @PathVariable("commentId") commentId: Long,
        @RequestBody @Valid command: CommentCreateCommand
    ): CommentDto {
        if (command.content == null) {
            throw BadRequestParameterException("댓글 내용은 필수항목입니다.")
        }
        return modifyCommentUseCase.execute(command.userId, commentId, command.content)
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{commentId}/user/{userId}")
    fun deleteComment(
        @PathVariable("commentId") commentId: Long,
        @PathVariable("userId") userId: Long,
    ): CommentDto {
        return deleteCommentUseCase.execute(userId, commentId)
    }
}
