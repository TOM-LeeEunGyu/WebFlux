package io.dustin.apps.board.api.usecase.community.comment

import io.dustin.apps.board.api.community.request.command.CommentModifyCommand
import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService
import io.dustin.apps.board.domain.community.comment.service.WriteCommentService
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ModifyCommentUseCase (

    private val readCommentService: ReadCommentService,
    private val writeCommentService: WriteCommentService,

    ) {

    fun execute(command: CommentModifyCommand): ResultResponse<CommentDto> {
        val comment: Comment = readCommentService.findByIdOrThrow(command.commentId)
        writeCommentService.updateContent(comment, command.content)
        val result = CommentDto.from(comment)
        return ResultResponse.of(result)
    }
}
