package io.dustin.apps.board.api.community.request.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

class PostingDeleteCommand (

    @field:NotNull(message = "userId는 필수값 입니다. 작성자만 삭제할 수 있습니다.")
    @Schema(description = "답글 작성자의  userId", example = "1")
    val userId: Long,

)
