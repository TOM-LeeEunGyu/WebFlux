package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(description = "게시물 삭제 요청 객체")
class PostingDeleteCommand (

    @Schema(description = "유저정보")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @Schema(description = "게시물 id 값", example = "1")
    @field:Min(1, message = "postingId는 필수입니다. 최소값은 1입니다.")
    val postingId: Long,



)
