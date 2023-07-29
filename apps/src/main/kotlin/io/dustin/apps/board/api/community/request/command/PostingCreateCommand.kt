package io.dustin.apps.board.api.community.request.command

data class PostingCreateCommand(
    val userId: Long,
    val subject: String,
    val content: String,
)