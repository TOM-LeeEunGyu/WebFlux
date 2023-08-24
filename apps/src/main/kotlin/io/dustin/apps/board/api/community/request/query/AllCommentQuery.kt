package io.dustin.apps.board.api.community.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Schema(description = "특정 게시물에 대한 댓글 리스트 요청 객체")
data class AllCommentQuery(

    @field:NotNull(message = "postingId는 필수값 입니다.")
    @Schema(description = "가져올 게시물에 대한 id값", example = "1")
    val postingId:Long,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 nestId 정보, 없으면 비운다.")
    val nextId: Long? = 0,

)
