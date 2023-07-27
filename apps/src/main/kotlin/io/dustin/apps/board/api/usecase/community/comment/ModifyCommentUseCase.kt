package io.dustin.apps.board.api.usecase.community.comment

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService
import io.dustin.apps.board.domain.community.comment.service.WriteCommentService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ModifyCommentUseCase (

    private val readCommentService: ReadCommentService,
    private val writeCommentService: WriteCommentService,

    ) {

    fun execute(userId: Long, commentId: Long, content: String): CommentDto {
        val comment: Comment = readCommentService.findByIdOrThrow(commentId)
        if (comment.userId != userId) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.")
        }
        writeCommentService.updateContent(comment, content)
        return CommentDto.from(comment)
    }
}
