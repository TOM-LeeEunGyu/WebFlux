package io.dustin.apps.board.api.community.request.command

class CommentUpdateCommand(
    val userId: Long,
    val replyId: Long?,
    val content: String,

) {
}