package io.dustin.apps.board.api.usecase.community.comment

import io.dustin.apps.board.api.community.request.command.CommentCreateCommand
import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.WriteCommentService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import io.dustin.apps.common.model.response.ResultResponse
import org.springframework.stereotype.Service

@Service
class WriteCommentUseCase (

    private val writeCommentService: WriteCommentService,
    private val writePostingService: WritePostingService

) {
    fun execute(command: CommentCreateCommand): ResultResponse<CommentDto> {
        val comment: Comment = writeCommentService.create(command.userId, command.postingId, command.replyId, command.content)
        writePostingService.commentCount(command.postingId)
        var result = CommentDto.from(comment)
        return ResultResponse.of(result)
    }
}
