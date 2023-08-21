package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Schema(description = "게시물 수정 관련 요청 객체")
data class PostingUpdateCommand(
    @Schema(description = "유저정보")
    @field:NotBlank(message = "유저 id 정보는 필수입니다.")
    val userId: Long,

    @field:NotBlank(message = "제목은 필수입니다.")
    @field:Min(5, message = "제목은 최소 5자이상 작성해야 합니다")
    val subject: String,

    @field:NotBlank(message = "내용은 필수입니다.")
    @field:Min(5, message = "내용은 최소 5자이상 작성해야 합니다")
    val content: String
) {
    fun checkAssignment() {
        if(subject == null && content == null) {
            throw IllegalArgumentException("업데이트 정보가 없습니다.")
        }
    }
}