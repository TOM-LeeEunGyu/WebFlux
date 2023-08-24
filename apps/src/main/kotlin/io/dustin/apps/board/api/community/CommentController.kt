package io.dustin.apps.board.api.community

import io.dustin.apps.board.api.community.request.command.CommentCreateCommand
import io.dustin.apps.board.api.community.request.command.CommentDeleteCommand
import io.dustin.apps.board.api.community.request.command.CommentModifyCommand
import io.dustin.apps.board.api.community.request.query.AllCommentQuery
import io.dustin.apps.board.api.community.request.query.CommentDetailQuery
import io.dustin.apps.board.api.community.request.query.PostingDetailQuery
import io.dustin.apps.board.api.usecase.community.comment.DeleteCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.ModifyCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase
import io.dustin.apps.board.api.usecase.community.comment.WriteCommentUseCase
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.common.code.CommonMessage
import io.dustin.apps.common.model.QueryPage
import io.dustin.apps.common.model.ResponseWithScroll
import io.dustin.apps.common.model.response.CommonResponse
import io.dustin.apps.common.model.response.ResultResponse
import io.dustin.apps.common.model.response.ResultResponsePagination
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
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/all")
    @Operation(
        summary = "댓글 리스트 API",
        description = "특정 게시물에 대한 모든 댓글을 가져온다."
    )
    fun commentListByPosting(
        @RequestBody @Valid query: PostingDetailQuery
    ): ResultResponsePagination<CommentDto> {
        return readCommentUseCase.execute(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/detail")
    @Operation(
        summary = "댓글 상세보기 API",
        description = "하나의 댓글에 대한 세부사항을 가져온다"
    )
    fun replyListByComment(
        @RequestBody @Valid query: CommentDetailQuery
    ): ResultResponsePagination<CommentDto> {
        return readCommentUseCase.replyListByComment(query)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("create")
    @Operation(
        summary = "댓글 작성 API",
        description = "댓글을 작성한다"
    )
    fun createComment(
        @RequestBody @Valid command: CommentCreateCommand
    ): ResultResponse<CommentDto> {
        /**
         * 해당 게시물에 댓글 수 증가로직 추가
         */
        return writeCommentUseCase.execute(command)
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/modify")
    @Operation(
        summary = "댓글 수정 관련 API",
        description = "댓글을 수정한다."
    )
    fun modifyComment(
        @RequestBody @Valid command: CommentModifyCommand
    ): ResultResponse<CommentDto> {
        return modifyCommentUseCase.execute(command)
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    @Operation(
        summary = "댓글 삭제 관련 API",
        description = "댓글을 삭제한다"
    )
    fun deleteComment(
        @RequestBody @Valid command: CommentDeleteCommand
    ): CommonResponse {
        deleteCommentUseCase.execute(command)
        return CommonResponse(
            code = HttpStatus.OK.value(),
            message = CommonMessage.SUCCESS.code,
            timestamp = LocalDateTime.now()
        )
    }
}
