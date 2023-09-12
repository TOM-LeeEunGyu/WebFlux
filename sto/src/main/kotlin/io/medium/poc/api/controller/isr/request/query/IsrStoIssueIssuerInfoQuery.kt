package io.medium.poc.api.controller.isr.request.query

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "발행사 게재정보접수_게재정보조회 조회 조건 요청 객체")
data class IsrStoIssueIssuerInfoQuery(
    @Schema(description = "발행인 번호")
    @field:NotNull(message = "발행인번호는 필수입니다.")
    @field:NotBlank(message = "발행인번호는 필수입니다.")
    val issueNo: String,
)