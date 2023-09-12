package io.medium.poc.api.controller.invest.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

@Schema(description = "투자자 토큰증권 목록조회 요청 객체")
data class InvestSearchConditionQuery(
    @Schema(description = "조회조건", example = "")
    val searchCondition: String = "",
    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,
    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val bookmark: Long = 0,
)