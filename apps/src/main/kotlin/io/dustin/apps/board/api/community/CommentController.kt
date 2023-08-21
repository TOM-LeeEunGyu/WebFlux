package io.dustin.apps.board.api.community

import io.dustin.apps.board.api.community.request.command.CommentCreateCommand
import io.dustin.apps.board.api.community.request.command.CommentUpdateCommand
import io.dustin.apps.board.api.usecase.community.comment.DeleteCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.ModifyCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.WriteCommentUseCase
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.exception.BadRequestParameterException
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import io.dustin.apps.common.model.response.CommonResponse
import io.dustin.apps.common.model.response.ResultResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api/v1/comment")
@Tag(name = "댓글 작성 관련 API", description = "댓글 작성,수정,삭제,조회 API 를 제공한다.")
class CommentController (

    private val readCommentUseCase: ReadCommentUseCase,
    private val writeCommentUseCase: WriteCommentUseCase,
    private val modifyCommentUseCase: ModifyCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,

    ) {
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{postingId}/all")
//    @Operation(
//        summary = "댓글 API",
//        description = "특정 게시물에 대한 모든 댓글을 가져온다."
//    )
//    fun commentListByPosting(
//        @PathVariable("postingId") postingId: Long,
//        queryPage: QueryPage
//    ): ResponseWithScroll<*> {
//        return readCommentUseCase.execute(postingId, queryPage)
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{commentId}")
    @Operation(
        summary = "댓글 상세보기 API",
        description = "하나의 댓글에 대한 세부사항을 가져온다"
    )
    fun replyListByComment(@PathVariable("commentId") commentId: Long, queryPage: QueryPage): ResponseWithScroll<*> {
        return readCommentUseCase.replyListByComment(commentId, queryPage)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("create")
    @Operation(
        summary = "게시물 관련 API",
        description = "작성된 모든 게시물을 가져온다."
    )
    fun createComment(
        @PathVariable("postingId") postingId: Long,
        @RequestBody @Valid command: CommentCreateCommand
    ): ResultResponse<CommentDto> {
        /**
         * 해당 게시물에 댓글 수 증가로직 추가
         */
        return writeCommentUseCase.execute(command.userId, postingId, command.replyId, command.content)
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{commentId}")
    @Operation(
        summary = "댓글 수정 관련 API",
        description = "댓글을 수정한다."
    )
    fun modifyComment(
        @PathVariable("commentId") commentId: Long,
        @RequestBody @Valid command: CommentUpdateCommand
    ): ResultResponse<CommentDto> {
        return modifyCommentUseCase.execute(command.userId, commentId, command.content)
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{commentId}/user/{userId}")
    @Operation(
        summary = "댓글 삭제 관련 API",
        description = "댓글을 삭제한다"
    )
    fun deleteComment(
        @PathVariable("commentId") commentId: Long,
        @PathVariable("userId") userId: Long,
    ): CommonResponse {
        deleteCommentUseCase.execute(userId, commentId)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}
