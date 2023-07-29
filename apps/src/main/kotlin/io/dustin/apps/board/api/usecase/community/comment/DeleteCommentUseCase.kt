package io.dustin.apps.board.api.usecase.community.comment

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService
import io.dustin.apps.board.domain.community.comment.service.WriteCommentService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class DeleteCommentUseCase (

    private val writePostingService: WritePostingService,
    private val readCommentService: ReadCommentService,
    private val writeCommentService: WriteCommentService,
) {

    @Transactional
    fun execute(userId: Long, commentId: Long): CommentDto {
        val comment: Comment = readCommentService.findByIdOrThrow(commentId)
        if (comment.userId != userId) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.")
        }
        writeCommentService.delete(comment)
        writePostingService.commentUnCount(comment.postingId)
        return CommentDto.from(comment)
    }
}
