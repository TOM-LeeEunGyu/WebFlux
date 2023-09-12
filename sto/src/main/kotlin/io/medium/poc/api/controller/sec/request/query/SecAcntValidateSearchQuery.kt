package io.medium.poc.api.controller.sec.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import java.time.LocalDate

@Schema(description = "위탁계좌유효성확인 리스트 조회 요청 객체")
data class SecAcntValidateSearchQuery(

    @Schema(description = "요청 날짜", example = "1900-01-01")
    val reqDt: LocalDate,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val bookmark: Long? = null,
)