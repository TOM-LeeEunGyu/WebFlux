package io.dustin.apps.board.api.community.request.command

class CommentCreateCommand(
    val userId: Long,
    val replyId: Long?,
    val content: String,

) {
}