package io.dustin.apps.board.api.qna.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

@Schema(description = "질문 리스트 요청 객체")
data class QuestionListQuery(

    @Schema(description = "유저 고유 id 값", example = "1")
    @field:Min(1, message = "userId는 필수입니다. 최소값은 1입니다.")
    val userId: Long,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val nextId: Long = 0,

)
