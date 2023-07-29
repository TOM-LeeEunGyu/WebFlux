package io.dustin.apps.board.api.community.request.command

data class PostingUpdateCommand(
    val userId: Long,
    val subject: String ?= null,
    val content: String ?= null,
) {
    fun checkAssignment() {
        if(subject == null && content == null) {
            throw IllegalArgumentException("업데이트 정보가 없습니다.")
        }
    }
}