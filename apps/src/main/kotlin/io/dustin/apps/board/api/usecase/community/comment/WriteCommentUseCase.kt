package io.dustin.apps.board.api.usecase.community.comment

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto
import io.dustin.apps.board.domain.community.comment.service.WriteCommentService
import io.dustin.apps.board.domain.community.posting.service.WritePostingService
import org.springframework.stereotype.Service

@Service
class WriteCommentUseCase (

    private val writeCommentService: WriteCommentService,
    private val writePostingService: WritePostingService

) {
    fun execute(userId: Long, postingId: Long, reply: Long, content: String): CommentDto {
        val comment: Comment = writeCommentService.create(userId, postingId, reply, content)
        writePostingService.commentCount(postingId)
        return CommentDto.from(comment)
    }
}
